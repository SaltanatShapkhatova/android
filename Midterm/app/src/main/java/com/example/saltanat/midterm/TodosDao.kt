package com.example.saltanat.midterm

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface NewsDao {

    @Query("Select * from todos")
    fun getAllNews () : Flowable<List<Todos>>

    @Insert
    fun insert(news : Todos)
}