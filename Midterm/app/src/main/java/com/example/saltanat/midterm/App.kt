package com.example.saltanat.midterm

import android.app.Application
import android.arch.persistence.room.Room

class App : Application() {
    companion object {
        lateinit var database: TodosDatabase
    }

    override fun onCreate() {
        database = Room.databaseBuilder(this, TodosDatabase::class.java, "todos-db")
                .allowMainThreadQueries()
                .build()
        super.onCreate()
    }
}