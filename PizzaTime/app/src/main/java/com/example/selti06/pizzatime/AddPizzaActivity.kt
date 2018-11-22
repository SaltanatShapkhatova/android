package com.example.selti06.pizzatime

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.arch.persistence.room.Room
import android.content.Intent
import android.transition.Slide
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.selti06.pizzatime.Model.ApiEndpoint
import com.example.selti06.pizzatime.Model.Pizza
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_add_pizza.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AddPizzaActivity : AppCompatActivity(), AddPizzaView {

    var addPizzaPresenter : AddPizzaPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pizza)
        addPizzaPresenter = AddPizzaPresenter(this)
    }

    override fun add() {
        /**Single.fromCallable{
            MainActivity.db?.pizzaDao()?.insert(Pizza(etTitle.text.toString(), etComposition.text.toString(), 0))
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()*/
        val gson = GsonBuilder().create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/SaltanatShapkhatova/android")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()
        val apiEndpoint = retrofit.create(ApiEndpoint::class.java)
        val call = apiEndpoint.getPizzas()
        call.enqueue(object : Callback<List<Pizza>> {

            override fun onResponse(call: Call<List<Pizza>>?, response: Response<List<Pizza>>?) {

                for(i in response?.body()!!.size.toString()){
                    val title = response?.body()?.get(i.toInt())!!.title
                    val composition = response?.body()?.get(i.toInt())!!.composition
                    val amount = response?.body()?.get(i.toInt())!!.amount
                    Single.fromCallable{
                        MainActivity.db?.pizzaDao()?.insert(Pizza(title, composition, amount))
                        finish()
                    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
                }
            }

            override fun onFailure(call: Call<List<Pizza>>?, t: Throwable?) {
                Log.e("Error with GET(): ", t?.message)
            }

        })
        var intent = Intent (this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun onClickInsert (view : View) {
        addPizzaPresenter?.validate(etTitle.text.toString(), etComposition.text.toString())
    }
}
