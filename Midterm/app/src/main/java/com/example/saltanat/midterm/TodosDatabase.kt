package com.example.saltanat.midterm

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase


@Database(entities = [Todos::class], version = 1)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todosDao() : TodosDao
}