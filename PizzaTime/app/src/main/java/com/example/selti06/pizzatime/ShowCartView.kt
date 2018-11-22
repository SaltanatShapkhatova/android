package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Pizza

interface ShowCartView {
    fun onResume()
    fun setItems(users : List<Pizza>)
    fun getItems() : List<Pizza>
}