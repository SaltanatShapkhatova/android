package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Pizza
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
            var items : ArrayList<Pizza> = mainView!!.getItems()
            mainView?.setItems(items)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}