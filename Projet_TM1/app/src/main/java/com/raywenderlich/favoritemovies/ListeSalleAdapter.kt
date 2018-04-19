package com.raywenderlich.favoritemovies

import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.Toast
import java.util.*


/**
 * Created by Yassine on 4/16/2018.
 */
class ListeSalleAdapter// data is passed into the constructor
(context: Context) : RecyclerView.Adapter<ListeSalleAdapter.ViewHolder>() {

    private var mViewColors = Collections.EMPTY_LIST
    private var mAnimals = Collections.EMPTY_LIST
    private var filmNames = Collections.EMPTY_LIST
    private var coverFilm= Collections.EMPTY_LIST
    private var mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null


    init {
        this.mInflater = LayoutInflater.from(context)
        //this.mViewColors = colors
        this.mAnimals = arrayListOf("Sale1", "Sale2", "Sale3", "Sale4", "Sale5", "Sale6")
        this.coverFilm = ArrayList<Int>()
        coverFilm.add(R.drawable.avatar2)
        coverFilm.add(R.drawable.samson)
        coverFilm.add(R.drawable.thenun)
        coverFilm.add(R.drawable.victorcrowley)
        coverFilm.add(R.drawable.blackwater)
        coverFilm.add(R.drawable.tombraider)
        /*coverFilm.add(R.drawable.annihilation)
        coverFilm.add(R.drawable.blackpanther)
        coverFilm.add(R.drawable.showmustgoon)
        coverFilm.add(R.drawable.beautyandthebeast)*/
        this.filmNames = ArrayList<String>()
        filmNames.add("Avatar2")
        filmNames.add("Samson")
        filmNames.add("The Nun")
        filmNames.add("Victor Crowley")
        filmNames.add("Black Water")
        filmNames.add("Tombraider")
        /*filmNames.add("Annihilation")
        filmNames.add("Black Panther")
        filmNames.add("Go On")
        filmNames.add("Beaty")*/
        //******
    }

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = mInflater.inflate(R.layout.salle_item, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the view and textview in each row
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val color:Int = mViewColors.get(position) as Int
        val animal = filmNames.get(position) as String
        val sale = mAnimals.get(position) as String
        val color:Int = coverFilm.get(position) as Int
        holder.myView.setImageResource(color)
        holder.myTextView.setText(animal)
        holder.nomSalle.setText(sale)

    }

    // total number of rows
    override fun getItemCount(): Int {
        return mAnimals.size
    }
    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var myView: ImageView
        var nomSalle: TextView
        var myTextView: TextView
        var rbar:RatingBar
        init {
            myView = itemView.findViewById(R.id.icon)
            nomSalle = itemView.findViewById(R.id.nomSale)
            myTextView = itemView.findViewById(R.id.sale)
            rbar = itemView.findViewById<RatingBar>(R.id.ratingBar)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
            //view.context.startActivity(Intent(this@DrawerActivity, activity.java))
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

