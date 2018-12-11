package com.example.selti06.finalproject.Model

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Contact::class, Group::class), version = 1)
abstract class ContactDb : RoomDatabase(){
    abstract fun contactDao() : ContactDao
    abstract fun groupDao() : GroupDao
}