package com.example.selti06.pizzatime

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.selti06.pizzatime.Model.Pizza
import kotlinx.android.synthetic.main.cart_item.view.*

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
        holder?.tvRemove!!.setOnClickListener(){
            //to be done
        }
    }
}
class CartViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var tvTitle = view.tvTitleCart
    var tvComposition = view.tvCompositionCart
    var etAmount = view.amntCart
    var minus = view.minusCart
    var plus = view.addCart
    var tvRemove = view.tvRemoveCart
}