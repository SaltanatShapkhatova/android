package com.example.selti06.pizzatime

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.selti06.pizzatime.Model.Order
import com.example.selti06.pizzatime.Model.Pizza
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.pizza_item.view.*

class PizzaAdapter (val items : List<Pizza>, val context : Context, private val listenerAdded: OnCartAdded) :
    RecyclerView.Adapter<PizzaAdapter.ViewHolder>()  {
    var cnt : Int = 0
    var totalPrice:Int = 0

    interface OnCartAdded {
        fun onAdd (position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
            .inflate(R.layout.pizza_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

        holder?.tvTitle!!.text = items.get(position).title
        holder?.tvComposition!!.text = items.get(position).composition
        holder?.tvPrice!!.text = items.get(position).price.toString()
        holder?.plus!!.setOnClickListener(){
            cnt = items.get(position).amount
            cnt++
            items.get(position).amount = cnt
            holder?.etAmount?.text = cnt.toString()
        }
        holder?.minus!!.setOnClickListener(){
            if(items.get(position).amount > 0) {
                cnt = items.get(position).amount
                cnt--
                items.get(position).amount = cnt
            }
            holder?.etAmount?.text = cnt.toString()
        }
        var contains : Boolean = false
        var orders = MainActivity.db!!.orderDao().getAllOrders()
        orders.forEach {
            if(it.title == items.get(position).title){
                contains = true
            }
        }
        holder?.tvAdd.setOnClickListener(){
            Log.d("Add", "add pressed")
            val titlePost : String = items.get(position).title
            val amountPost : Int = items.get(position).amount
            val compositionPost : String = items.get(position).composition
            val pricePost:Int = items.get(position).price
            if(amountPost!=0 && contains == false){
                holder?.tvSelect.visibility = View.INVISIBLE
                totalPrice += amountPost*pricePost
                listenerAdded.onAdd(totalPrice)
                Single.fromCallable{
                    MainActivity.db?.orderDao()?.insert(Order(titlePost, compositionPost,amountPost, pricePost))
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
            }
            else if(amountPost == 0){
                holder?.tvSelect.visibility = View.VISIBLE
            }else{
                holder?.tvSelect.setText("Already added")
                holder?.tvSelect.visibility = View.VISIBLE
            }
         }
    }

    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        var tvTitle = view.tvTitle
        var tvComposition = view.tvComposition
        var etAmount = view.amnt
        var minus = view.minus
        var plus = view.add
        var tvAdd = view.tvAdd
        var tvSelect = view.tvSelection
        var tvPrice = view.tvPrice
    }
}
