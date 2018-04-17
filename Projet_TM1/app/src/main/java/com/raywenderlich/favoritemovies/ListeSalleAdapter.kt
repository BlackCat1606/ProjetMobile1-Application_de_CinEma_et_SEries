package com.raywenderlich.favoritemovies

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.*

/**
 * Created by Yassine on 4/16/2018.
 */



class ListeSalleAdapter(private val context:Activity, private val itemname: Array<String>)// TODO Auto-generated constructor stub
    : ArrayAdapter<String>(context, R.layout.salle_item, itemname) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.salle_item, null, true)
        val txtTitle = rowView.findViewById<View>(R.id.item) as TextView
        val imageView = rowView.findViewById<View>(R.id.icon) as ImageView
        txtTitle.text = itemname[position]
        imageView.setImageResource(R.drawable.salleiconne)
        return rowView

    }
}