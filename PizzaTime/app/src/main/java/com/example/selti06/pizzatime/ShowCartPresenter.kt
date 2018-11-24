package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Pizza
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ShowCartPresenter {
    var showCartView : ShowCartView?=null
    constructor(view : ShowCartView) {
        showCartView = view
    }
    fun onResume() {
        Single.fromCallable{
            var items : List<Pizza> = showCartView!!.getItems()
            showCartView?.setItems(items)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}