package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MusicaDao {

    @Query("SELECT * FROM Musica")
    fun getAllMusic(): List<Musica>

    @Insert
    fun insertMusic(music: Musica)
}