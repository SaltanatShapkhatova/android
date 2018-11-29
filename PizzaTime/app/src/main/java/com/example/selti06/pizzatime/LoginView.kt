package com.example.selti06.pizzatime

interface LoginView {

    fun onLoginSuccess()
    fun onLoginFailed (message : String?)
    fun setLastEmail(email: String)
}