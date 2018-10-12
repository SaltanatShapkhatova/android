package com.example.saltanat.midterm

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.saltanat.midterm.App.Companion.database

import com.example.saltanat.midterm.dummy.DummyContent
import com.example.saltanat.midterm.dummy.DummyContent.DummyItem
import kotlinx.android.synthetic.main.fragment_item.*

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ItemFragment.OnListFragmentInteractionListener] interface.
 */

class ItemFragment : Fragment() {

    companion object {
        fun newInstance(): ItemFragment {
            val fragment = ItemFragment()
            val arguments = Bundle()
            arguments.putString("title", "Facebook reports jump in profits")
            arguments.putString("url", "https://cdn-st2.rtr-vesti.ru/vh/pictures/hdr/169/821/7.jpg")
            arguments.putString("date", "05-11-2015")
            fragment.arguments = arguments
            return fragment
        }
    }

    lateinit var title: String
    lateinit var url: String
    lateinit var date: String

    var list: ArrayList<Todos> = ArrayList()
    lateinit var adapter : TodosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = TodosAdapter (this, list, this)

        database.todosDao().getAllNews().subscribe {
            list = it as ArrayList<Todos>
            adapter.notifyDataSetChanged()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)

        return view;
    }
}
