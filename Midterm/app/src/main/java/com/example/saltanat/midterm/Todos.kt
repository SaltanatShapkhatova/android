package com.example.saltanat.midterm

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todos")
data class Todos(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val title : String,
        val date : String,
        val body : String
)