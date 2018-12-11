package com.example.selti06.finalproject.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "groups")
data class Group(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "priority")
    var priority: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}