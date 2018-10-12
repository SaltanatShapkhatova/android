package com.example.saltanat.mynewsapp

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable


@Dao
interface NewsDao {

    @Query("Select * from news")
    fun getAllNews () : Flowable<List<News>>


    @Insert
    fun insert(news : News)
}