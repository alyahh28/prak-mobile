package com.example.alyah_apps

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.example.alyah_apps.Home.pertemuan_2.SecondActivity
import com.example.alyah_apps.Home.pertemuan_3.ThirdActivity
import com.example.alyah_apps.Home.pertemuan_4.FourthActivity
import com.example.alyah_apps.Home.pertemuan_5.FifthActivity
import com.example.alyah_apps.Home.pertemuan_7.SeventhActivity
import com.example.alyah_apps.Home.pertemuna_9.NIneActivity
import kotlin.jvm.java

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan layout yang dipanggil adalah activity_main yang baru saja diperbaiki
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)


        findViewById<Button>(R.id.btnToSecond).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.btnToThird).setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        findViewById<Button>(R.id.btnToFourth).setOnClickListener {
            startActivity(Intent(this, FourthActivity::class.java))
        }

        findViewById<Button>(R.id.btnToFifth).setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        // Pertemuan 7
        findViewById<Button>(R.id.btnToSeventh).setOnClickListener {
            startActivity(Intent(this, SeventhActivity::class.java))
        }

        // Pertemuan 9
        findViewById<Button>(R.id.btnToNine).setOnClickListener {
            startActivity(Intent(this, NIneActivity::class.java))
        }

        // --- Logika Logout ---
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    // Hapus Session
                    sharedPref.edit { clear() }

                    // Pindah ke AuthActivity (Login)
                    val intent = Intent(this, BaseActivity::class.java)
                    // Flag ini memastikan user tidak bisa tekan 'back' kembali ke MainActivity
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}