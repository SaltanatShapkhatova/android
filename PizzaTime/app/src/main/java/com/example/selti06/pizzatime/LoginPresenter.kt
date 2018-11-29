package com.example.selti06.pizzatime

import android.annotation.SuppressLint
import com.example.selti06.pizzatime.MainActivity.Companion.db
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(private val view : LoginView) {


    fun login(email: String, password: String) {
        val auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                view.onLoginSuccess ()
            } else {
                view.onLoginFailed(it.exception?.message)
            }
        }
    }



    @SuppressLint("CheckResult")
    fun getLastEmail() {
        db?.userDao()?.getUsers()?.subscribe {
            if (it.isNotEmpty())
                view.setLastEmail(it.last().email)
        }
    }
}