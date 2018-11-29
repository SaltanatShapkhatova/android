package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.MainActivity.Companion.db
import com.example.selti06.pizzatime.Model.User
import com.google.firebase.auth.FirebaseAuth

class RegisterPresenter(private val view : RegisterView) {


    fun register(email: String, password: String, name : String, surname : String) {
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = User(
                    0,
                    auth.currentUser?.uid,
                    email,
                    password,
                    name,
                    surname
                )
                db?.userDao()?.insertUser(user)
                view.onRegisterSuccess()
            } else {
                view.onRegisterFailed(it.exception?.message)
            }
        }
    }
}