package com.example.saltanat.actorkotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.view.View

class MyAdapter(private val actorList: List<Actor>) :
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mName: TextView
        var mFilm: TextView

        init {
            mName = itemView.findViewById(R.id.actor_name)
            mFilm = itemView.findViewById(R.id.actor_film)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {

        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.row, parent, false) as TextView

        return MyViewHolder(textView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentActor = actorList[position]
        holder.mName.text = currentActor.name
        holder.mFilm.text = currentActor.film
    }

    override fun getItemCount() = actorList.size
}