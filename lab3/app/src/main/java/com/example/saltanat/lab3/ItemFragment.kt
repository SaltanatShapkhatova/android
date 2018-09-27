package com.example.saltanat.lab3

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_item.view.*

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
    lateinit var listener: OnItemSelectedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null){
            title = arguments!!.getString("title")
            url = arguments!!.getString("url")
            date = arguments!!.getString("date")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item, container, false)
        view.iv.setImageBitmap(BitmapFactory.decodeFile(url))
        view.tvTitle.setText(title)
        view.tvDate.setText(date)
        view.tvTitle.setOnClickListener({
            listener.onItemSelected()
        })
        return view;
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemSelectedListener)
            listener = context
    }

    public interface OnItemSelectedListener {
        fun onItemSelected()
    }

}
