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
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
            .inflate(R.layout.pizza_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
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
        holder?.tvAdd.setOnClickListener(){
            val gson = GsonBuilder().create()
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
            okHttpClient.addInterceptor(interceptor)
            val retrofit = Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient.build())
                .build()
            val apiEndpoint = retrofit.create(ApiEndpoint::class.java)
            val post = Post(283325, "Saltanat",items.get(position).id,items.get(position).amount,
                items.get(position).title, items.get(position).composition)
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