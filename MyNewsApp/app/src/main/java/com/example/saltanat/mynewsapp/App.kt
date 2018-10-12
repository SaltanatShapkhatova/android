package com.example.saltanat.mynewsapp

import android.app.Application
import android.arch.persistence.room.Room

class App : Application() {
    companion object {
        lateinit var database: NewsDatabase
    }

    override fun onCreate() {
        database = Room.databaseBuilder(this, NewsDatabase::class.java, "news-db")
                .allowMainThreadQueries()
                .build()
        super.onCreate()
    }
}