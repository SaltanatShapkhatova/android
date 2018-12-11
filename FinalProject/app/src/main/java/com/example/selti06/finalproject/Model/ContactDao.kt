package com.example.selti06.finalproject.Model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ContactDao {

    @Query("Select * from contacts")
    fun getContacts () : Flowable<List<Contact>>

    @Insert
    fun insertContact (contact: Contact)

    @Query("Select name from contacts, groups where gId == id")
    fun getGroupName(id : Int)
}

@Dao
interface GroupDao {

    @Query("Select * from groups")
    fun getGroups () : Flowable<List<Group>>

    @Insert
    fun insertGroup (group: Group)
}