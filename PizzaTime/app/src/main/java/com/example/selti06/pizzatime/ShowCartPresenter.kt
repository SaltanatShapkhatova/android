package com.example.selti06.pizzatime

import com.example.selti06.pizzatime.Model.Order
import com.example.selti06.pizzatime.ShowCartActivity.Companion.db
import com.google.firebase.auth.FirebaseAuth
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
            var items : ArrayList<Order> = showCartView!!.getItems()
            showCartView?.setItems(items)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun removeById(id: Int) {
        Single.fromCallable{
            db?.orderDao()?.deleteOrder(id)
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun logout() {
        val auth = FirebaseAuth.getInstance()
        auth.signOut()
        showCartView?.showMessage("Good Bye!")
        showCartView?.onLogoutSuccess()
    }
}