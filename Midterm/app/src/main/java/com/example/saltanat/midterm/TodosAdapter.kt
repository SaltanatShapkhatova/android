package com.example.saltanat.midterm

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todos.view.*

class TodosAdapter(private val context : Context,
                  private val items : ArrayList<Todos>,
                  private val listener : TodosItemClicked)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return TodosViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_todos, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val todos = items[p1]
        p0.itemView.tvDate.text = todos.date
        p0.itemView.tvTitle.text = todos.title

        p0.itemView.setOnClickListener {
            listener.onItemClicked(todos)
        }
    }


    class TodosViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView)

}

interface TodosItemClicked {
    fun onItemClicked (todos : Todos)
}