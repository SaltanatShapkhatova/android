package com.example.selti06.pizzatime

import android.support.v4.os.IResultReceiver

import com.example.selti06.pizzatime.Model.Pizza

interface MainView {
    fun onResume()
    fun setItems(items : ArrayList<Pizza>)
    fun getItems() : ArrayList<Pizza>
    fun onLogoutSuccess ()
    fun showMessage(message: String)
}