package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.FragmentReadBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.models.ReadMusica
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.preferences.AppUse
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room.Musica
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room.MusicaDatabase
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room.MusicaRoomAdapter
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException

class ReadFragment : Fragment() {
    lateinit var binding : FragmentReadBinding
    lateinit var db: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var database : MusicaDatabase
    lateinit var allMusic : List<Musica>
    var storageFire = FirebaseStorage.getInstance()
    var intro: MutableList<ReadMusica> = ArrayList()
    var reproducir = false
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Room.databaseBuilder(requireContext(), MusicaDatabase::class.java, "musica_database").allowMainThreadQueries().build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDb()
        allMusic = database.MusicaDao().getAllMusic()
        binding.btnReproducir.isEnabled = false
        rellenarDatos()
        reproductor()
        setListener()

    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.reset()
    }

    fun reproductor(){
        AppUse.reproduciendo.observe(requireActivity(), Observer {
            if(AppUse.reproduciendo.value == true) {
                binding.btnReproducir.isEnabled = true
                mediaPlayer.stop()
                mediaPlayer.reset()
                reproducir = false
                reproducir()
            }
        })
        AppUse.reproduciendoLocal.observe(requireActivity(), Observer {
            if(AppUse.reproduciendoLocal.value == true){
                binding.btnReproducir.isEnabled = true
                mediaPlayer.stop()
                mediaPlayer.reset()
                reproducir = false
                reproducirRoom()
            }
        })
    }

    fun setListener(){
        binding.btnReproducir.setOnClickListener {
            reproducir()
        }
    }

    fun reproducir(){
        if (!reproducir) {
            reproducir = true
            binding.btnReproducir.setImageResource(android.R.drawable.ic_media_pause)
            try {
                if(mediaPlayer.currentPosition > 1) {
                        mediaPlayer.start()
                    }else{
                        var audioUrl = AppUse.cancion
                        var storageRef = storageFire.getReferenceFromUrl("$audioUrl.mp3")
                        storageRef.downloadUrl.addOnSuccessListener() {
                            var url = it.toString()
                            mediaPlayer.reset()
                            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                            mediaPlayer.setDataSource(url)
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                            binding.tvAutorReproductor.text = AppUse.autor
                            binding.tvNombreReproductor.text = AppUse.nombre
                        }
                    }

            } catch (e: IOException) {
                e.printStackTrace()
            }
            Log.d("Escuchando audio...", "Escuchando audio...")
        } else {
            mediaPlayer.pause()
            binding.btnReproducir.setImageResource(android.R.drawable.ic_media_play)
            reproducir = false

        }
    }

    fun reproducirRoom(){
        if (!reproducir) {
            reproducir = true
            binding.btnReproducir.setImageResource(android.R.drawable.ic_media_pause)
            try {
                if(mediaPlayer.currentPosition > 1) {
                    mediaPlayer.start()
                }else{
                    var uri = Uri.parse(AppUse.cancion)
                        mediaPlayer.reset()
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                        mediaPlayer.setDataSource(requireContext(), uri)
                        mediaPlayer.prepare()
                        mediaPlayer.start()
                        binding.tvAutorReproductor.text = AppUse.autor
                        binding.tvNombreReproductor.text = AppUse.nombre

                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
            Log.d("Escuchando audio...", "Escuchando audio...")
        } else {
            mediaPlayer.pause()
            binding.btnReproducir.setImageResource(android.R.drawable.ic_media_play)
            reproducir = false

        }
    }

    private fun rellenarDatos(){
        intro.clear()
        reference.get()
        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                intro.clear()
                for(messageSnapshot in snapshot.children){
                    val music = messageSnapshot.getValue<ReadMusica>(ReadMusica::class.java)
                    if(music != null){
                        intro.add(music)
                    }
                }
                setRecycler(intro as ArrayList<ReadMusica>)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }

    private fun setRecycler(lista: ArrayList<ReadMusica>){
        val linearLayoutManager = LinearLayoutManager(context)
        var total = ConcatAdapter(MusicaAdapter(lista), MusicaRoomAdapter(allMusic))
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = total
        binding.recyclerview.scrollToPosition(lista.size-1)

    }

    private fun initDb(){
        db = FirebaseDatabase.getInstance("https://practicafinal2jgga-default-rtdb.firebaseio.com/")
        reference = db.getReference("musica")
    }

    companion object {

        @JvmStatic
        fun newInstance(): ReadFragment{
            return ReadFragment()
        }

    }
}