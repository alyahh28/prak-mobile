package com.example.alyah_apps.pertemuan_4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alyah_apps.R
import com.example.alyah_apps.databinding.ActivityFourthBinding
import com.example.alyah_apps.databinding.ActivityThirdBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log


class FourthActivity : AppCompatActivity() {
    lateinit var binding: ActivityFourthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFourthBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnKembali.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)


            setSupportActionBar(binding.toolbar)
            supportActionBar?.apply {
                title = "Activity Fifth"
                subtitle = "Ini adalah subtitle"
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
            val name = intent.getStringExtra("name")
            val from = intent.getStringExtra("from")
            val age = intent.getIntExtra("age", 0)
            Log.i("Data Intent", "Nama: $name , Usia: $age, Asal: $from")
            }

        Log.e("onCreate", "FourthActivity dibuat pertama kali")

        binding.btnShowSnackbar.setOnClickListener {
            Snackbar.make(binding.root, "Ini adalah Snackbar", Snackbar.LENGTH_SHORT)
                .setAction("Tutup") {
                    Log.e("Info Snackbar", "Snackbar ditutup")
                }
                .show()
        }

        binding.btnShowAlertDialog.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Konfirmasi")
                .setMessage("Apakah Anda yakin ingin melanjutkan?")
                .setPositiveButton("Konfirm") { dialog, _ ->

                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Ya!")
                }
                .setNegativeButton("Kembali") { dialog, _ ->
                    dialog.dismiss()
                    Log.e("Info Dialog", "Anda memilih Tidak!")
                }
                .show()
        }

        binding.btnKembali.setOnClickListener {
            onDestroy()
        }
    }
    override fun onStart() {
        super.onStart()
        Log.e("onStart", "onStart: {nama_activity} terlihat di layar")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("onDestroy", "{nama_activity} dihapus dari stack")
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    }

