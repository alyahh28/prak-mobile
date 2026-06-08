package com.example.alyah_apps.Home.pertemuan_13

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alyah_apps.R
import com.example.alyah_apps.databinding.FragmentTabScanBinding

class TabScanFragment : Fragment() {
    private var _binding: FragmentTabScanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentTabScanBinding.inflate(inflater, container, false)
        return binding.root
    }
}