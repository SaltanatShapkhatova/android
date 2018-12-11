package com.example.selti06.finalproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.selti06.finalproject.MainActivity.Companion.db
import com.example.selti06.finalproject.Model.Contact
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity(), AddView {

    var addPresenter : AddPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        addPresenter = AddPresenter(this)

        spinner.setOnClickListener(){
            ArrayAdapter.createFromResource(
                this,
                db?.groupDao().getGroups(),
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
            }
        }
    }

    override fun add() {
            Single.fromCallable{
                MainActivity.db?.contactDao()?.insertContact(Contact(edtName.text.toString(), edtMobile.text.toString(),
                    edtHome.text.toString(), edtWork.text.toString(), spinner.getSelectedItem().toString().toInt()))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()

        var intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onClickInsert (view : View) {
        addPresenter?.validate(edtName.text.toString(), edtMobile.text.toString())
    }
}
