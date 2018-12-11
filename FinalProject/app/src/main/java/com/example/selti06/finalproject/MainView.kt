package com.example.selti06.finalproject

import com.example.selti06.finalproject.Model.Contact

interface MainView {
    fun onResume()
    fun setItems(items : ArrayList<Contact>)
    fun getItems() : ArrayList<Contact>
}