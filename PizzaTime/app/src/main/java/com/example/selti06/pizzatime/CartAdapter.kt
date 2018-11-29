package com.example.selti06.pizzatime

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.selti06.pizzatime.Model.Order
import kotlinx.android.synthetic.main.cart_item.view.*

class CartAdapter (val items : ArrayList<Order>, val context : Context, private val listenerRemoved: OnCartRemoved) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()  {
    var cnt : Int = 0

    interface OnCartRemoved {
        fun onRemove (position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return CartViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.cart_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder?.itemView.tvTitleCart?.text = items.get(position).title
        holder?.itemView.tvCompositionCart?.text = items.get(position).composition
        holder?.itemView.amntCart?.text = items.get(position).amount.toString()

        items.get(position).amount = 0
        holder?.itemView.addCart!!.setOnClickListener(){
            cnt++
            items.get(position).amount = cnt
            holder?.itemView.amntCart?.text = cnt.toString()
        }
        holder?.itemView.minusCart!!.setOnClickListener(){
            if(items.get(position).amount > 0)
                cnt--
            items.get(position).amount = cnt
            holder?.itemView.amntCart?.text = cnt.toString()
        }
        holder?.itemView.tvRemoveCart!!.setOnClickListener(){
            Log.d("DELETE", "deleted")
            listenerRemoved.onRemove(position)
        }
    }
    class CartViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

}