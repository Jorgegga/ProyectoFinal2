package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.R
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases.MusicaViewHolder

class MusicaRoomAdapter(private val allMusic: List<Musica>) : RecyclerView.Adapter<MusicaRoomViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaRoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.musicaroom_layout, parent, false)
        return MusicaRoomViewHolder(v)
    }

    override fun onBindViewHolder(holder: MusicaRoomViewHolder, position: Int) {
        val musica = allMusic[position]
        holder.render(musica)
    }

    override fun getItemCount(): Int {
        return allMusic.size
    }


}