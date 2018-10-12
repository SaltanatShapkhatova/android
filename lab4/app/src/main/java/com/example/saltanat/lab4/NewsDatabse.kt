package com.example.saltanat.lab4

import android.arch.persistence.room.Database

@Database(entities = [News::class], version = 1)
abstract class NewsDatabse {
    abstract fun newsDao() : NewsDao
}