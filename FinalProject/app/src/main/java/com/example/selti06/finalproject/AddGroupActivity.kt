package com.example.selti06.finalproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.selti06.finalproject.Model.Group
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_group.*

class AddGroupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_group)
        btnGroup.setOnClickListener(){
            Single.fromCallable{
                MainActivity.db?.groupDao()?.insertGroup(Group(etNameGroup.text.toString(), etPriority.text.toString()))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
        }
    }
}
