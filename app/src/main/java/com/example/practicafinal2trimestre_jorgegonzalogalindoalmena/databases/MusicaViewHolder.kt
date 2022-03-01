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
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class MusicaViewHolder(v: View ): RecyclerView.ViewHolder(v){
    private val binding = MusicaLayoutBinding.bind(v)
    lateinit var db: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var mediaPlayer: MediaPlayer
    var storageFire = FirebaseStorage.getInstance()
    var reproducir = false

    fun render(musica : ReadMusica){
        initDb()

        binding.tvTitulo.text = musica.nombre
        binding.tvAlbum.text = musica.autor
        binding.btnPlay.setOnClickListener {
            if(!reproducir) {
                reproducir = true
                binding.btnPlay.setImageResource(android.R.drawable.ic_media_pause)
                var audioUrl = musica.ruta
                mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                try {
                    var storageRef = storageFire.getReferenceFromUrl("$audioUrl.mp3")
                    storageRef.downloadUrl.addOnSuccessListener() {
                        var url = it.toString()
                        mediaPlayer.setDataSource(url)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                    }

                } catch (e: IOException) {
                    e.printStackTrace()
                }
                Log.d("Escuchando audio...", "Escuchando audio...")
            }else{
                mediaPlayer.stop()
                binding.btnPlay.setImageResource(android.R.drawable.ic_media_play)
                reproducir = false
            }
        }
    }

    fun fetchAudioUrlFromFirebase(urlMusica : String){
        var storage = FirebaseStorage.getInstance()
        var storageRef = storage.getReferenceFromUrl("$urlMusica.mp3")
        //storageRef.downloadUrl.addOnSuccessListener(OnSuccessListener)
    }

    private fun initDb(){
        db = FirebaseDatabase.getInstance("https://practicafinal2jgga-default-rtdb.firebaseio.com/")
        reference = db.getReference("musica")
    }
}