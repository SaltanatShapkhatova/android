package com.example.selti06.finalproject

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.selti06.finalproject.Model.Contact
import kotlinx.android.synthetic.main.contact_item.view.*
import android.support.v4.content.ContextCompat.startActivity
import android.content.Intent
import com.example.selti06.finalproject.MainActivity.Companion.db


class ContactAdapter (val items : ArrayList<Contact>, val context : Context) :
    RecyclerView.Adapter<ContactAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.tvName!!.text = items.get(position).name
        holder?.tvName.setOnClickListener(){
            val intent = Intent(context, DetailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("name", items.get(position).name)
            intent.putExtra("mobile", items.get(position).mobile)
            intent.putExtra("home", items.get(position).home)
            intent.putExtra("work", items.get(position).work)
            intent.putExtra("image", items.get(position).image)
            intent.putExtra("group", db?.contactDao()!!.getGroupName(items.get(position).id).toString())
            context.startActivity(intent)
        }
    }

    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        var tvName = view.tvName
        var tvContactGroup = view.tvContactGroup
    }
}