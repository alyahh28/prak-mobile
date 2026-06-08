package com.example.alyah_apps.Home.pertemuan_13

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alyah_apps.R
import com.example.alyah_apps.databinding.ActivityThirteenthBinding
import com.google.android.material.tabs.TabLayoutMediator

class ThirteenthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirteenthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Inisialisasi View Binding untuk Pertemuan 13
        binding = ActivityThirteenthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2. Window Insets handling menggunakan ID "main" dari XML
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Pertemuan 13"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 4. Inisialisasi Adapter Pertemuan 13
        val tabsAdapter = ThirteenthTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 5. Menghubungkan TabLayout dan ViewPager2 dengan Judul & Icon yang Sesuai Fragment
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Capture"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_capture) // Ganti icon jika ada khusus capture
                    // Contoh badge titik merah
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
                1 -> {
                    tab.text = "QR Code"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_qr)
                    // Contoh badge dengan angka
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 5
                }
                2 -> {
                    tab.text = "Scan"
                    tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_scan)
                    // Misal untuk tab ketiga hanya teks saja atau bisa ditambah icon
                }
            }
        }.attach()
    }

    // 6. Logika Tombol Kembali (Back) di Toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}