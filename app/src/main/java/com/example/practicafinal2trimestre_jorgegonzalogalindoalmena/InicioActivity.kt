package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena


import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases.CrearFragment
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases.ReadFragment
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.ActivityInicioBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.maps.MapsFragment
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.preferences.Prefs
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.webview.WebFragment
import com.google.android.material.navigation.NavigationView


class InicioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    lateinit var binding : ActivityInicioBinding
    lateinit var transaction : FragmentTransaction
    lateinit var fragmentPortada : Fragment
    lateinit var fragmentMaps : Fragment
    lateinit var fragmentWeb : Fragment
    lateinit var fragmentCrear : Fragment
    lateinit var fragmentRead : Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolbar()
        setHeader()
        title="Scarlet Perception"
        fragmentPortada = PortadaFragment()
        fragmentMaps = MapsFragment()
        fragmentWeb = WebFragment()
        fragmentCrear = CrearFragment()
        fragmentRead = ReadFragment()
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

    fun setHeader(){
        var prefs = Prefs(this)
        var navView = findViewById<NavigationView>(R.id.nav_view)
        var header = navView.getHeaderView(0)
        var tvCorreo = header.findViewById<TextView>(R.id.tvCorreo)
        tvCorreo.text = prefs.leerEmail()

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("aaaaaaaaaaaa", item.toString())
        transaction = supportFragmentManager.beginTransaction()
        when(item.itemId){
            R.id.btnInicio -> {
                transaction.replace(R.id.fragmentContainerView, fragmentPortada).commit()
                transaction.addToBackStack(null)
                item.isChecked = true
                binding.drawerLayout.close()
                return true
            }

            R.id.btnCanciones ->{
                transaction.replace(R.id.fragmentContainerView, fragmentRead).commit()
                transaction.addToBackStack(null)
                item.isChecked = true
                binding.drawerLayout.close()
                return true
            }

            R.id.btnSubir ->{
                transaction.replace(R.id.fragmentContainerView, fragmentCrear).commit()
                transaction.addToBackStack(null)
                item.isChecked = true
                binding.drawerLayout.close()
                return true
            }

            R.id.btnMaps ->{
                transaction.replace(R.id.fragmentContainerView, fragmentMaps).commit()
                transaction.addToBackStack(null)
                item.isChecked = true
                binding.drawerLayout.close()
                return true
            }

            R.id.btnWeb ->{
                transaction.replace(R.id.fragmentContainerView, fragmentWeb).commit()
                transaction.addToBackStack(null)
                item.isChecked = true
                binding.drawerLayout.close()
                return true
            }

            else ->{
                return false
            }
        }
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount

        if (count == 0) {
            super.onBackPressed()
            //additional code
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    companion object{

    }

}