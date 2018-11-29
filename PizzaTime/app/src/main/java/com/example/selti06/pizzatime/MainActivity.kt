package com.example.selti06.pizzatime

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.example.selti06.pizzatime.Model.*
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), MainView {

     lateinit  var adapter : PizzaAdapter
     var list  = ArrayList<Pizza>()
     companion object {
         var db: PizzaDb? = null
     }

     var mainPresenter = MainPresenter(this)
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)
         db = Room.databaseBuilder(this, PizzaDb::class.java, "userDb")
             .fallbackToDestructiveMigration()
             .allowMainThreadQueries()
             .build()
         recyclerView.layoutManager = LinearLayoutManager(this)
         adapter = PizzaAdapter(list, this)
         recyclerView.adapter = adapter

         val gson = GsonBuilder().create()
         val interceptor = HttpLoggingInterceptor()
         interceptor.level = HttpLoggingInterceptor.Level.BODY
         val okHttpClient = OkHttpClient.Builder()
             .readTimeout(60, TimeUnit.SECONDS)
             .connectTimeout(60, TimeUnit.SECONDS)
         okHttpClient.addInterceptor(interceptor)
         val retrofit = Retrofit.Builder()
             .baseUrl("https://my-json-server.typicode.com/SaltanatShapkhatova/android/")
             .addConverterFactory(GsonConverterFactory.create(gson))
             .client(okHttpClient.build())
             .build()
         val apiEndpoint = retrofit.create(ApiEndpoint::class.java)
         val call = apiEndpoint.getPizzas()
         val listPizzas  = db?.pizzaDao()?.getAllPizzas()
         call.enqueue(object : Callback<List<Pizza>> {

             override fun onResponse(call: Call<List<Pizza>>?, response: Response<List<Pizza>>?) {

                 for(i in response?.body()!!.size.toString()){
                     val title = response?.body()?.get(i.toInt())!!.title
                     val composition = response?.body()?.get(i.toInt())!!.composition
                     val amount = response?.body()?.get(i.toInt())!!.amount
                     if(!listPizzas!!.contains(Pizza(title,composition,amount))){
                         Single.fromCallable{
                             MainActivity.db?.pizzaDao()?.insert(Pizza(title, composition, amount))
                             finish()
                         }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
                     }
                 }
             }

             override fun onFailure(call: Call<List<Pizza>>?, t: Throwable?) {
                 Log.e("Error with GET(): ", t?.message)
             }
         })
     }

     override fun onResume() {
         super.onResume()
         mainPresenter.onResume()
     }

     override fun setItems(items: ArrayList<Pizza>) {
         list.addAll(items)
         adapter.notifyDataSetChanged()
     }

     override fun getItems(): ArrayList<Pizza> {
         return db?.pizzaDao()!!.getAllPizzas() as ArrayList<Pizza>

     }

     fun onClickAdd(view: View) {
         val intent = Intent(this, ShowCartActivity::class.java)
         startActivity(intent)
     }
 }