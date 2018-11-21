package com.example.selti06.pizzatime

import android.support.v4.os.IResultReceiver

import com.example.selti06.pizzatime.Model.Pizza

interface MainView {
    fun onResume()
    fun setItems(users : List<Pizza>)
    fun getItems() : List<Pizza>
}