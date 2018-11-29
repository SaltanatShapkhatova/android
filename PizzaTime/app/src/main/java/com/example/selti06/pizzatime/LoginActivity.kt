package com.example.selti06.pizzatime

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    private val presenter : LoginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            presenter.login(emailEdtTxt.text.toString(), passEdtTxt.text.toString())
        }

        presenter.getLastEmail()

        tvRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {

                checkbox.setText(R.string.passwordHide)// change
                passEdtTxt.setInputType(InputType.TYPE_CLASS_TEXT);
                passEdtTxt.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance())// show password
            } else {
                checkbox.setText(R.string.passwordShow)// change
                passEdtTxt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD)
                passEdtTxt.setTransformationMethod(
                    PasswordTransformationMethod
                    .getInstance())// hide password

            }

        }
    }

    override fun onLoginSuccess() {
        val intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onLoginFailed(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun setLastEmail(email: String) {
        emailEdtTxt.setText(email)
    }
}