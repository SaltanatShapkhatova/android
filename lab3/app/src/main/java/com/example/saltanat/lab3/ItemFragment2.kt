package com.example.saltanat.lab3

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.saltanat.lab3.dummy.DummyContent
import com.example.saltanat.lab3.dummy.DummyContent.DummyItem

class ItemFragment2 : Fragment() {


    companion object {
        fun newInstance(): ItemFragment2 {
            return ItemFragment2()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item2, container, false)

        return view
    }
}
