package com.example.selti06.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun setItems() {

        intent.getStringExtra("image")
        tvName.setText(intent.getStringExtra("name"))
        tvMobile.setText(intent.getStringExtra("mobile"))
        tvHome.setText(intent.getStringExtra("home"))
        tvWork.setText(intent.getStringExtra("work"))
        iv.setImageResource(intent.getStringExtra("image").toInt())
        tvGroup.setText(intent.getStringExtra("group"))
    }
}
