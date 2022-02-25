package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena


import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.ActivityInicioBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.fullscreen.Principal
import com.google.android.material.navigation.NavigationView


class InicioActivity : Principal(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var binding : ActivityInicioBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        pantallaCompleta()
    }

    fun setToolbar(){
        val toolbar: Toolbar = binding.mainToolbar
        setSupportActionBar(toolbar)

        var drawerLayout = binding.drawerLayout
        var navigationView = binding.navView

        var actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavDrawer,
            R.string.closeNavDrawer
        )
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return false
    }

}