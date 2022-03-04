package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.MusicaroomLayoutBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.preferences.AppUse

class MusicaRoomViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val binding = MusicaroomLayoutBinding.bind(v)

    fun render(musica : Musica){
        binding.tvTituloRoom.text = musica.nombre
        binding.tvAutorRoom.text = musica.autor
        binding.btnPlayRoom.setOnClickListener {
            AppUse.nombre = musica.nombre
            AppUse.autor = musica.autor
            AppUse.cancion = musica.musica
            AppUse.reproduciendoLocal.value = true
            AppUse.reproduciendoLocal.value = false
        }
    }
}