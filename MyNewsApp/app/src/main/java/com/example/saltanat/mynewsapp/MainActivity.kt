package com.example.saltanat.mynewsapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.saltanat.mynewsapp.App.Companion.database
import com.example.saltanat.mynewsapp.R.id.rv
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NewsItemClicked {

    val code = 1

    override fun onItemClicked(news: News) {
        val intent = Intent (this, NewsDetailsActivity::class.java)
        intent.putExtra("title", news.title)
        intent.putExtra("date", news.date)
        intent.putExtra("content", news.body)
        startActivity(intent)
    }

    var list: ArrayList<News> = ArrayList()
    lateinit var adapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter (this, list, this)
        rv.adapter = adapter

        database.newsDao().getAllNews().subscribe {
            runOnUiThread {
                list.clear()
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }

        fab.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == code) {
            if (resultCode == Activity.RESULT_OK) {
                database.newsDao().getAllNews().subscribe {
                    list = it as ArrayList<News>
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
