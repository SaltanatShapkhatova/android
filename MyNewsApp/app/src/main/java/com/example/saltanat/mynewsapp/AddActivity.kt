package com.example.saltanat.mynewsapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.saltanat.mynewsapp.App.Companion.database
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        addButton.setOnClickListener {
            database.newsDao().insert(News(0, titleValue.text.toString(),
                    dateValue.text.toString(), contentValue.text.toString()))

            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
