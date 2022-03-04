package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Musica(
    @PrimaryKey(autoGenerate = true) val uid : Int = 1,
    val nombre: String,
    val autor: String,
    val musica: String
)
