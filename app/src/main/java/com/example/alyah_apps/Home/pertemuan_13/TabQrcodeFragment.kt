package com.example.alyah_apps.Home.pertemuan_13

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alyah_apps.databinding.FragmentTabQrcodeBinding
import com.example.alyah_apps.utils.NotificationHelper
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter

// Pastikan import ini sesuai dengan lokasi berkas Helper & Target Activity Anda
// import com.example.alyah_apps.utils.NotificationHelper
// import com.example.alyah_apps.ThirdResultActivity

class TabQrcodeFragment : Fragment() {
    private var _binding: FragmentTabQrcodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTabQrcodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Menyesuaikan logika klik tombol dengan ID asli file Anda: btnGenerate
        binding.btnGenerate.setOnClickListener {
            val text = binding.edtQrInput.text.toString().trim()
            if (text.isEmpty()) return@setOnClickListener

            // 1. Logika asli: Membuat dan menampilkan QR Code
            binding.ivQrCode.setImageBitmap(createQR(text))

            // 2. Logika tambahan: Mengirim Notifikasi menggunakan requireContext()
            val intent = Intent(requireContext(), ThirteenthActivity::class.java)

            NotificationHelper.showNotification(
                requireContext(),
                "QR Anda",
                "Halo $text, QR Anda Sudah Diproses",
                intent
            )
        }
    }

    private fun createQR(text: String): Bitmap {
        val writer = QRCodeWriter()
        val matrix = writer.encode(
            text,
            BarcodeFormat.QR_CODE,
            500,
            500,
            mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")
        )
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565)
        for (x in 0 until 500) {
            for (y in 0 until 500) {
                bitmap.setPixel(x, y, if (matrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        return bitmap
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}