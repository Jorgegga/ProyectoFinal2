package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.R
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.models.ReadMusica

class MusicaAdapter(private val lista: ArrayList<ReadMusica>): RecyclerView.Adapter<MusicaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.musica_layout, parent, false)
        return MusicaViewHolder(v)
    }

    override fun onBindViewHolder(holder: MusicaViewHolder, position: Int) {
        val musica = lista[position]
        holder.render(musica)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}