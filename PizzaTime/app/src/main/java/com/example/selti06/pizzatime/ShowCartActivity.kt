package com.example.selti06.pizzatime

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.selti06.pizzatime.Model.Order
import com.example.selti06.pizzatime.Model.Pizza
import com.example.selti06.pizzatime.Model.PizzaDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_cart.*

class ShowCartActivity : AppCompatActivity(), ShowCartView{

    companion object {
        var db: PizzaDb? = null
    }

    var showCartPresenter = ShowCartPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = Room.databaseBuilder(this, PizzaDb::class.java, "userDb")
            .fallbackToDestructiveMigration()
            .build()
//        recyclerViewCart.layoutManager = LinearLayoutManager(this)
    }
    override fun onResume() {
        super.onResume()
        showCartPresenter.onResume()
    }

    override fun getItems(): List<Order> {
        return ShowCartActivity.db?.orderDao()!!.getAllOrders()
    }

    override fun setItems(users: List<Order>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onClickOrder(view: View) {
        //do post
    }
}
