package com.example.alyah_apps.pertemuan_5

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.alyah_apps.R
import com.example.alyah_apps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // WAJIB ADA: Biar Toolbar & Menu muncul
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webView.apply {
            settings.javaScriptEnabled = true

            // NOMOR 1 & 2: Subtitle Loading
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    supportActionBar?.title = "Memuat..."
                    supportActionBar?.subtitle = url
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    supportActionBar?.title = "Web Merdeka"
                    supportActionBar?.subtitle = "Selesai"
                }
            }
            loadUrl("https://merdeka.com")

            // NOMOR 3: Scroll Hide/Show (Pasti jalan kalau XML-nya CoordinatorLayout)
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    binding.appBar.setExpanded(false, true)
                } else {
                    binding.appBar.setExpanded(true, true)
                }
            }
        }
    }

            // NOMOR 4: Ganti ke Radio Button (Tema Warna)
            override fun onCreateOptionsMenu(menu: Menu?): Boolean {
                val subMenu = menu?.addSubMenu("Ganti Tema")
                // Group ID 100 agar jadi Radio Button
                subMenu?.add(100, 1, 1, "Default (Ungu)")?.setCheckable(true)?.isChecked = true
                subMenu?.add(100, 2, 2, "Biru")?.setCheckable(true)
                subMenu?.setGroupCheckable(100, true, true)
                return true
            }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            return when (item.itemId) {
                android.R.id.home -> {
                    onBackPressed(); true
                } // NOMOR 5
                1 -> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#6200EE")); item.isChecked =
                        true; true
                }

                2 -> {
                    binding.toolbar.setBackgroundColor(Color.parseColor("#2196F3")); item.isChecked =
                        true; true
                }

                else -> super.onOptionsItemSelected(item)
            }
        }

        // NOMOR 5: Fungsi Back agar bisa digunakan
        override fun onBackPressed() {
            if (binding.webView.canGoBack()) {
                binding.webView.goBack()
            } else {
                super.onBackPressed()
            }
        }

    }