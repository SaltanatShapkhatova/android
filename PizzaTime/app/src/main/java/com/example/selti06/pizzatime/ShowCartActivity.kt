package com.example.selti06.pizzatime

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.selti06.pizzatime.Model.*
import com.google.gson.GsonBuilder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_show_cart.*
import kotlinx.android.synthetic.main.pizza_item.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.TemporalAmount
import java.util.concurrent.TimeUnit

class ShowCartActivity : AppCompatActivity(), ShowCartView, CartAdapter.OnCartRemoved,
    CartAdapter.OnCartChanged, CartAdapter.OnCartChangedMinus{

    override fun onRemove(position: Int) {
        Log.d("AMOUNT", list[position].amount.toString())
        showCartPresenter.removeById(list[position].id)
        list.removeAt(position)
        adapter.notifyItemRemoved(position)
        calc()
        Log.d("TOTALPRICE", totalInt.toString())
    }

    override fun onChanged(price :Int) {

        totalInt += price
        tvTotalPrice.text = totalInt.toString()
    }

    override fun onChangedMinus(price :Int) {

        totalInt -= price
        tvTotalPrice.text = totalInt.toString()
    }


    lateinit  var adapter : CartAdapter
    var list  = ArrayList<Order>()
    var totalInt : Int = 0

    companion object {
        var db: PizzaDb? = null
    }

    var showCartPresenter = ShowCartPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_cart)
        db = Room.databaseBuilder(this, PizzaDb::class.java, "userDb")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
        recyclerViewCart.layoutManager = LinearLayoutManager(this)

        btnMenu.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        adapter = CartAdapter(list, this, this,this, this)
        recyclerViewCart.adapter = adapter
    }

    fun calc(){
        list.forEach {
            totalInt+=(it.amount*it.price)
        }
        if(totalInt == 0){
            tvTotalPrice.setText("0")
        }else{
            tvTotalPrice.setText(totalInt.toString())
        }
    }

    override fun onResume() {
        super.onResume()
        showCartPresenter.onResume()
    }

    override fun getItems(): ArrayList<Order> {
        return ShowCartActivity.db?.orderDao()!!.getAllOrders() as ArrayList<Order>
    }

    override fun setItems(arraylist: ArrayList<Order>) {

        list.addAll(arraylist)
        adapter.notifyDataSetChanged()
        calc()
    }

    override fun onLogoutSuccess() {
        val intent = Intent (this, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun onClickOrder(view: View) {

        val gson = GsonBuilder().create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(interceptor)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000/pizza_list/cart/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient.build())
            .build()
        var orders = db!!.orderDao().getAllOrders()
        Log.d("ORDER: ", list.toString())
        orders.forEach{
            if(it.amount != 0){
                val apiEndpoint = retrofit.create(ApiEndpoint::class.java)
                val post = Post(1, "Salta", amount = it.amount, title = it.title,
                    composition = it.composition)
                val call = apiEndpoint.createPost(post)
                call.enqueue(object : Callback<Post> {

                    override fun onResponse(call: Call<Post>?, response: Response<Post>?) {
                        val post = response?.body()
                        Log.d("Post: ", post.toString())
                    }

                    override fun onFailure(call: Call<Post>?, t: Throwable?) {
                        Log.e("Error: ", t?.message)
                    }
                })

                /**list.removeAt(it.id)
                adapter.notifyItemRemoved(it.id)

                showCartPresenter.removeById(it.id)*/
                Toast.makeText(this,"Ordered succesfully", Toast.LENGTH_LONG).show()
            }

        }
    }
}
