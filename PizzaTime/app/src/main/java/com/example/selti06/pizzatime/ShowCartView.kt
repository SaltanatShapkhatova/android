package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Order

interface ShowCartView {
    fun onResume()
    fun setItems(users : List<Order>)
    fun getItems() : List<Order>
}