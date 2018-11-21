 package com.example.selti06.pizzatime

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.selti06.pizzatime.Model.*
import kotlinx.android.synthetic.main.activity_main.*

 class MainActivity : AppCompatActivity(), MainView {
     companion object {
         var db: PizzaDb? = null
     }

     var mainPresenter = MainPresenter(this)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         db = Room.databaseBuilder(this, PizzaDb::class.java, "userDb")
             .fallbackToDestructiveMigration()
             .build()
         recyclerView.layoutManager = LinearLayoutManager(this)
     }

     override fun onResume() {
         super.onResume()
         mainPresenter.onResume()
     }

     override fun setItems(items: List<Pizza>) {
         recyclerView.adapter = items.let { PizzaAdapter(it, this) }
     }

     fun onClick(){

     }

     override fun getItems(): List<Pizza> {
         return db?.pizzaDao()!!.getAllPizzas()
     }

     fun onClickAdd(view: View) {
         /*var intent = Intent(this, AddPizzaActivity::class.java)
         startActivity(intent)*/
         var intent = Intent(this, ShowCartActivity::class.java)
         startActivity(intent)
     }


 }