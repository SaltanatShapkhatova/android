package com.example.selti06.pizzatime

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.selti06.pizzatime.Model.ApiEndpoint
import com.example.selti06.pizzatime.Model.Pizza
import com.example.selti06.pizzatime.Model.Post
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_show_cart.view.*
import kotlinx.android.synthetic.main.pizza_item.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CartAdapter (val items : List<Pizza>, val context : Context) :
    RecyclerView.Adapter<CartViewHolder>()  {
    var cnt : Int = 0
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.cart_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CartViewHolder?, position: Int) {
        holder?.tvTitle?.text = items.get(position).title
        holder?.tvComposition?.text = items.get(position).composition

        items.get(position).amount = 0
        holder?.plus!!.setOnClickListener(){
            cnt++
            items.get(position).amount = cnt
            holder?.etAmount?.text = cnt.toString()
        }
        holder?.minus!!.setOnClickListener(){
            if(items.get(position).amount > 0)
                cnt--
            items.get(position).amount = cnt
            holder?.etAmount?.text = cnt.toString()
        }
        holder?.btnOrder!!.setOnClickListener(){
            //to be done
        }
    }
}
class CartViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var tvTitle = view.tvTitle
    var tvComposition = view.tvComposition
    var etAmount = view.amnt
    var minus = view.minus
    var plus = view.add
    var btnOrder = view.btnOrder
}