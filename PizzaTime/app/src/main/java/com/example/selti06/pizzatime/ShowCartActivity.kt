package com.example.selti06.pizzatime

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.selti06.pizzatime.Model.Pizza
import kotlinx.android.synthetic.main.activity_show_cart.*

class ShowCartActivity : AppCompatActivity(), ShowCartView{

    var showCartPresenter = ShowCartPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cart)
    }

    override fun getItems(): List<Pizza> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setItems(users: List<Pizza>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onClickOrder(view: View) {
        //do post
    }
}
