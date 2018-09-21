package com.example.saltanat.actorkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.support.design.R.attr.layoutManager
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var actorList: MutableList<Actor>? = ArrayList()
    private var fab: FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewManager = LinearLayoutManager(this)
        if (savedInstanceState == null) {
            actorList = ArrayList()
        } else {
            actorList = savedInstanceState.getParcelableArrayList(ACTOR_LIST)
        }

        if (actorList != null) {
            viewAdapter = MyAdapter(actorList!!)
        }

        recyclerView = findViewById<RecyclerView>(R.id.my_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }
        fab = findViewById<View>(R.id.fab) as FloatingActionButton
        fab!!.setOnClickListener {
            val actor = Actor("John John", "Great Film")
            actorList!!.add(actor)
            viewAdapter!!.notifyDataSetChanged()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (actorList != null && !actorList!!.isEmpty()) {
            outState.putParcelableArrayList(ACTOR_LIST, actorList as ArrayList<out Parcelable>?)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)

        if (savedInstanceState != null) {
            actorList = savedInstanceState.getParcelableArrayList(ACTOR_LIST)
        }
    }

    companion object {
        private val ACTOR_LIST = "actor_list"
    }
}
