package com.example.saltanat.lab4

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable


@Entity(tableName = "news")
data class News(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val title : String,
        val date : String,
        val body : String
)