package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.InicioActivity
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.R
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.MusicaLayoutBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.models.ReadMusica
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.preferences.AppUse
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class MusicaViewHolder(v: View ): RecyclerView.ViewHolder(v){
    private val binding = MusicaLayoutBinding.bind(v)

    fun render(musica : ReadMusica){
        binding.tvTitulo.text = musica.nombre
        binding.tvAlbum.text = musica.autor
        binding.btnPlay.setOnClickListener {
            AppUse.nombre = musica.nombre
            AppUse.autor = musica.autor
            AppUse.cancion = musica.ruta
            AppUse.reproduciendo.value = true
            AppUse.reproduciendo.value = false
        }
    }

}