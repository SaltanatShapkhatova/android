package com.example.selti06.pizzatime

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.persistence.room.Room
import android.content.Intent
import android.transition.Slide
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.selti06.pizzatime.Model.Pizza
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_pizza.*

class AddPizzaActivity : AppCompatActivity(), AddPizzaView {

    var addPizzaPresenter : AddPizzaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pizza)
        addPizzaPresenter = AddPizzaPresenter(this)
    }

    override fun add() {
        Single.fromCallable{
            MainActivity.db?.pizzaDao()?.insert(Pizza(etTitle.text.toString(), etComposition.text.toString(), 0))
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onClickInsert (view : View) {
        addPizzaPresenter?.validate(etTitle.text.toString(), etComposition.text.toString())
    }
}
