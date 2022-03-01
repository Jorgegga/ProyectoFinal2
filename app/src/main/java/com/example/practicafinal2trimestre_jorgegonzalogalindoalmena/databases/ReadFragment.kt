package com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databases

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.databinding.FragmentReadBinding
import com.example.practicafinal2trimestre_jorgegonzalogalindoalmena.models.ReadMusica
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class ReadFragment : Fragment() {
    lateinit var binding : FragmentReadBinding
    lateinit var db: FirebaseDatabase
    lateinit var reference: DatabaseReference
    var storageFire = FirebaseStorage.getInstance()
    var intro: MutableList<ReadMusica> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        rellenarDatos()
        Log.d("aaaaaaaa", intro.toString())
    }

    private fun rellenarDatos(){
        reference.get()
        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
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
        binding.recyclerview.layoutManager = linearLayoutManager
        binding.recyclerview.adapter = MusicaAdapter(lista)
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