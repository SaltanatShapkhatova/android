package com.example.saltanat.mynewsapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        pref = getSharedPreferences("Settings", Context.MODE_PRIVATE)

        loginBtn.setOnClickListener() {
            val editor = pref.edit()

            editor.putString("email", emailEdtTxt.text.toString())
            editor.putString("password", passEdtTxt.text.toString())
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
