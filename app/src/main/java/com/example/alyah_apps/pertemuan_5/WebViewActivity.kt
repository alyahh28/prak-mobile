package com.example.alyah_apps.pertemuan_5

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
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

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    supportActionBar?.title = "Memuat..."
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    supportActionBar?.title = "Web Merdeka"
                    supportActionBar?.subtitle = "Selesai"
                }
            }
            loadUrl("https://merdeka.com")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val subMenu = menu?.addSubMenu("Ganti Tema")
        subMenu?.add(100, 1, 1, "Default")?.setCheckable(true)?.isChecked = true
        subMenu?.add(100, 2, 2, "Biru")?.setCheckable(true)
        subMenu?.setGroupCheckable(100, true, true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if (binding.webView.canGoBack()) binding.webView.goBack() else finish()
                true
            }
            1 -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#6200EE"))
                item.isChecked = true
                true
            }
            2 -> {
                binding.toolbar.setBackgroundColor(Color.parseColor("#2196F3"))
                item.isChecked = true
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}