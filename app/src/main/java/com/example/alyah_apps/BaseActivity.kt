package com.example.alyah_apps

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.alyah_apps.Home.HomeFragment
import com.example.alyah_apps.Message.MessageFragment
import com.example.alyah_apps.More.MoreFragment
import com.example.alyah_apps.databinding.ActivityBaseBinding

class BaseActivity : AppCompatActivity() {

    // 1. Deklarasi binding harus di level class, bukan di dalam onCreate
    private lateinit var binding: ActivityBaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 2. Inisialisasi View Binding dengan benar
        binding = ActivityBaseBinding.inflate(layoutInflater)

        enableEdgeToEdge()

        // 3. Gunakan root dari binding sebagai content view
        setContentView(binding.root)

        // 4. Gunakan ID yang sesuai dari layout kamu (biasanya 'main' adalah ID root layout)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        // 5. Setup Listener untuk Bottom Navigation
        /** FragmentHome sebagai fragment default */
        replaceFragment(HomeFragment())

        binding.bottomNavView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.message -> {
                    replaceFragment(MessageFragment())
                    true
                }
                R.id.more -> {
                    replaceFragment(MoreFragment())
                    true
                }
                else ->false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
            //.addToBackStack(null) -> ini kita nonaktifkan agar saat back langsung keluar aplikasi
            .commit()
    }
}