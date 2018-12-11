package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Order

interface ShowCartView {
    fun onResume()
    fun setItems(list : ArrayList<Order>)
    fun getItems() : ArrayList<Order>
    fun onLogoutSuccess ()
    fun showMessage(message: String)
}