package com.example.saltanat.mynewsapp

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "news")
data class News(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val title : String,
        val date : String,
        val body : String
)