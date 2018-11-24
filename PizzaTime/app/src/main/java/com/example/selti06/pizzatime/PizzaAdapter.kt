package com.example.selti06.pizzatime

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
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
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.pizza_item.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class PizzaAdapter (val items : List<Pizza>, val context : Context) :
    RecyclerView.Adapter<ViewHolder>()  {
    var cnt : Int = 0
    var posts : List<Pizza> = emptyList<Pizza>()
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
            .inflate(R.layout.pizza_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

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
        holder?.tvAdd.setOnClickListener(){
            val titlePost : String = items.get(position).title
            val amountPost : Int = items.get(position).amount
            val compositionPost : String = items.get(position).composition
            Single.fromCallable{
                MainActivity.db?.orderDao()?.insert(Pizza(titlePost, compositionPost,amountPost))
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
            //posts+=listOf(Pizza(titlePost, compositionPost,amountPost))

        }
    }
}
class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var tvTitle = view.tvTitle
    var tvComposition = view.tvComposition
    var etAmount = view.amnt
    var minus = view.minus
    var plus = view.add
    var tvAdd = view.tvAdd
}