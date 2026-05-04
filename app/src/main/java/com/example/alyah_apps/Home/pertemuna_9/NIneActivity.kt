package com.example.alyah_apps.Home.pertemuna_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.alyah_apps.R
import com.example.alyah_apps.databinding.ActivityNineBinding // Pastikan import ini ada
import com.google.android.material.chip.Chip

class NIneActivity : AppCompatActivity() {

    // 1. Deklarasikan variabel binding
    private lateinit var binding: ActivityNineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. Inisialisasi View Binding
        binding = ActivityNineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Atur padding untuk sistem bar (status bar/navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Logika Toolbar (Pindahkan ke luar insets listener)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed() // Cara terbaru untuk back
        }

        // 4. Logika ChipGroup
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}