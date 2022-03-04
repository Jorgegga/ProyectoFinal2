package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.preferences

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import java.util.prefs.Preferences

class AppUse: Application() {
    companion object{
        val BASE="baseUsuarios"
        val TABLA = "tablaUsuarios"
        val VERSION = 1
        lateinit var appContext : Context
        //lateinit var DB: MyDataBase
        lateinit var prefs: Prefs
        var reproduciendo : MutableLiveData<Boolean> = MutableLiveData(false)
        var reproduciendoLocal : MutableLiveData<Boolean> = MutableLiveData(false)
        var nombre = ""
        var autor = ""
        var cancion = ""
    }

    override fun onCreate() {
        super.onCreate()
        prefs = Prefs(applicationContext)
        appContext = applicationContext
        //DB = MyDataBase()
    }
}