package com.example.alyah_apps.Home.pertemuan_13

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.alyah_apps.databinding.FragmentTabScanBinding
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TabScanFragment : Fragment() {

    private lateinit var cameraExecutor: ExecutorService
    private var _binding: FragmentTabScanBinding? = null
    private val binding get() = _binding!!

    // Khusus hanya format QR Code
    private val scanner = BarcodeScanning.getClient(BarcodeScannerOptions.Builder()
        .setBarcodeFormats(Barcode.FORMAT_QR_CODE)
        .build()
    )

    // Launcher untuk izin kamera
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(context, "Izin kamera diperlukan untuk melakukan scan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTabScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi Executor
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Cek izin kamera saat view dibuat
        if (hasCameraPermission()) {
            startCamera()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun hasCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            try {
                val cameraProvider = cameraProviderFuture.get()

                // Preview setup
                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(binding.previewView.surfaceProvider)
                }

                // Image Analyzer setup
                val imageAnalyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .apply {
                        setAnalyzer(cameraExecutor) { imageProxy ->
                            processImageProxy(imageProxy)
                        }
                    }

                // Pilih kamera belakang
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                // Unbind all dan bind ke lifecycle
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    viewLifecycleOwner, 
                    cameraSelector, 
                    preview, 
                    imageAnalyzer
                )

            } catch (e: Exception) {
                Log.e("TabScan", "Gagal mulai kamera: ${e.message}", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun processImageProxy(imageProxy: androidx.camera.core.ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)
            scanner.process(image)
                .addOnSuccessListener { barcodes ->
                    if (barcodes.isNotEmpty()) {
                        val rawValue = barcodes[0].rawValue
                        activity?.runOnUiThread {
                            binding.tvScanResult.text = "Hasil Scan: $rawValue"
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("TabScan", "Scan gagal: ${e.message}")
                }
                .addOnCompleteListener {
                    imageProxy.close()
                }
        } else {
            imageProxy.close()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraExecutor.shutdown()
        _binding = null
    }
}
