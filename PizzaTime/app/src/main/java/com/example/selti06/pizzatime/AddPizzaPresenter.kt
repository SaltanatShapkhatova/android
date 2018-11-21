package com.example.selti06.pizzatime

class AddPizzaPresenter{
    var addPizzaView : AddPizzaView?=null
    constructor(view : AddPizzaView) {
        addPizzaView = view
    }
    fun validate (name : String, surname : String) {
        if (name.isEmpty()) {
            addPizzaView?.showMessage("Title")
            return
        }
        if (surname.isEmpty()){
            addPizzaView?.showMessage("Composition")
            return
        }
        addPizzaView?.add()
    }
}