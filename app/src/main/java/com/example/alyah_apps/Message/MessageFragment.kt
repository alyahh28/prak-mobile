package com.example.alyah_apps.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alyah_apps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    // Inisialisasi View Binding
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // 1. Definisi list data message menggunakan MessageModel
    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Budi", "Sudah makan?", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Eka", "Nice job kemarin!", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Fajar", "Lagi ngapain?", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Gita", "Boleh minta tolong?", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Hana", "Lihat email ya", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Irfan", "Oke noted", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Joko", "Sampai jumpa besok", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate layout menggunakan binding
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 2. Inisialisasi Toolbar
        binding.toolbar.title = "Message"

        // 3. Inisialisasi MessageAdapter (Custom Adapter)
        // Menggunakan requireContext() dan data messageList yang sudah didefinisikan
        val adapter = MessageAdapter(requireContext(), messageList)

        // 4. Hubungkan ListView dengan Adapter
        // Pastikan ID pada fragment_message.xml adalah listMessageItems
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Bersihkan binding untuk menghindari memory leak
        _binding = null
    }
}