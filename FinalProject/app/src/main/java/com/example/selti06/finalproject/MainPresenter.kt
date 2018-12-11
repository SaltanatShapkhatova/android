package com.example.selti06.finalproject

import com.example.selti06.finalproject.Model.Contact
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter {
    var mainView : MainView?=null
    constructor(view : MainView) {
        mainView = view
    }
    fun onResume() {
        Single.fromCallable{
            var items : ArrayList<Contact> = mainView!!.getItems()
            mainView?.setItems(items)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}