package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Musica::class], version = 1)
abstract class MusicaDatabase : RoomDatabase() {
    abstract fun MusicaDao(): MusicaDao
}