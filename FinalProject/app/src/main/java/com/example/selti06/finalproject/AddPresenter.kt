package com.example.selti06.finalproject

class AddPresenter{
    var addView : AddView?=null
    constructor(view : AddView) {
        addView = view
    }
    fun validate (name : String, mobile : String) {
        if (name.isEmpty()) {
            addView?.showMessage("Name")
            return
        }
        if (mobile.isEmpty()){
            addView?.showMessage("Mobile")
            return
        }
        addView?.add()
    }
}