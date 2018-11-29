package com.example.selti06.pizzatime

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterView {

    private val presenter: RegisterPresenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        bnRegister.setOnClickListener {
            presenter.register(
                emailEdtTxt.text.toString(), passEdtTxt.text.toString(),
                etName.text.toString(), etSurname.text.toString()
            )
        }

        checkboxRegister.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                checkboxRegister.setText(R.string.passwordHide)// change
                passEdtTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                passEdtTxt.setTransformationMethod(
                    HideReturnsTransformationMethod
                        .getInstance())
            } else {
                checkboxRegister.setText(R.string.passwordShow)// change
                passEdtTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                passEdtTxt.setTransformationMethod(
                    PasswordTransformationMethod
                        .getInstance())
            }
        }
    }

    override fun onRegisterSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onRegisterFailed(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
