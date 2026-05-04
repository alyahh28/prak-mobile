package com.example.alyah_apps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.alyah_apps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. Inisialisasi Binding
        val binding: ItemMessageBinding = if (convertView == null) {
            ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)
        } else {
            ItemMessageBinding.bind(convertView)
        }

        val view = binding.root
        val data = getItem(position)

        if (data != null) {
            // 2. Binding data ke View
            binding.textSender.text = data.senderName
            binding.textMessage.text = data.messageText

            // 3. Load gambar menggunakan Glide
            Glide.with(context)
                .load(data.avatarUrl)
                .into(binding.avatarImg)

            // 4. TERAPKAN ONCLICK PADA SETIAP ITEM (Tambahan Terbaru)
            view.setOnClickListener {
                Snackbar.make(
                    parent,
                    "Pesan dari ${data.senderName}: ${data.messageText}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        return view
    }
}