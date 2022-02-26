package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena


import android.content.res.Resources
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.ActivityInicioBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.fullscreen.Principal
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.maps.MapsFragment
import com.google.android.material.navigation.NavigationView


class InicioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var binding : ActivityInicioBinding
    lateinit var transaction : FragmentTransaction
    lateinit var fragmentPortada : Fragment
    lateinit var fragmentMaps : Fragment
    lateinit var fragmentWeb : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        title="Scarlet Perception"
        fragmentPortada = PortadaFragment()
        fragmentMaps = MapsFragment()

        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, fragmentPortada).commit()
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
        transaction = supportFragmentManager.beginTransaction()
        when(item.itemId){
            R.id.btnInicio -> {
                transaction.replace(R.id.fragmentContainerView, fragmentPortada).commit()
                return true
            }

            R.id.btnMaps ->{
                transaction.replace(R.id.fragmentContainerView, fragmentMaps).commit()
                return true
            }
            else ->{
                return false
            }
        }
    }

}