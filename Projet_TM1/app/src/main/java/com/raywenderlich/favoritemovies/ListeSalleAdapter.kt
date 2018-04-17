package com.raywenderlich.favoritemovies

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import java.util.*


/**
 * Created by Yassine on 4/16/2018.
 */
class ListeSalleAdapter// data is passed into tie constructor
(context: Context, animals: List<String>) : RecyclerView.Adapter<ListeSalleAdapter.ViewHolder>() {

    private var mViewColors = Collections.EMPTY_LIST
    private var mAnimals = Collections.EMPTY_LIST
    private var mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    init {
        this.mInflater = LayoutInflater.from(context)
        this.mAnimals = animals
    }
    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.salle_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = mAnimals.get(position) as String
        holder.myTextView.setText(animal)
    }

    // total number of rows
    override fun getItemCount(): Int {
        return mAnimals.size
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var myView: ImageView
        var myTextView: TextView

        init {
            myView = itemView.findViewById(R.id.icon)
            myTextView = itemView.findViewById(R.id.item)
            myView.setImageResource(R.drawable.salleiconne)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }
    }

    // convenience method for getting data at click position
    fun getItem(id: Int): String {
        return mAnimals.get(id) as String
    }

    // allows clicks events to be caught
    fun setClickListener(itemClickListener: ItemClickListener) {
        this.mClickListener = itemClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}

