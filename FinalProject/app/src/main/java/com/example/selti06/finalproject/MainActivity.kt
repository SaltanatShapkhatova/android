package com.example.selti06.finalproject

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.selti06.finalproject.Model.Contact
import com.example.selti06.finalproject.Model.ContactDb
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    lateinit  var adapter : ContactAdapter
    var list  = ArrayList<Contact>()
    companion object {
        var db: ContactDb? = null
    }

    var mainPresenter = MainPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(this, ContactDb::class.java, "contactDb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ContactAdapter(list, this)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener(){
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.onResume()
    }

    override fun setItems(items: ArrayList<Contact>) {
        list.addAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun getItems(): ArrayList<Contact> {
        return db?.contactDao()!!.getContacts() as ArrayList<Contact>

    }
}
