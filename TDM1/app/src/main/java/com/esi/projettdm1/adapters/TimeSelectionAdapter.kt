package com.esi.projettdm1.adapters

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView

import com.esi.projettdm1.R
import com.esi.projettdm1.data.MovieTime
import com.esi.projettdm1.utils.FontChanger
import com.squareup.picasso.Picasso



class TimeSelectionAdapter(private val movieTimeList: List<MovieTime>, internal var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class MovieTimeHolder(view: View) : RecyclerView.ViewHolder(view) {

        var movieTimeTV: TextView
        var progressBar: ProgressBar

        init {

            movieTimeTV = view.findViewById(R.id.movieTimeTV)
            progressBar = view.findViewById(R.id.seatsProgress)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.time_column_item, parent, false)
        return MovieTimeHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val movieTime = movieTimeList[position]
        val movieTimeHolder = holder as MovieTimeHolder
        movieTimeHolder.movieTimeTV.setText(movieTime.time)
        movieTimeHolder.progressBar.max = movieTime.totalSeats

        val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
        val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")

        movieTimeHolder.movieTimeTV.typeface = font

        movieTimeHolder.progressBar.progress = movieTime.totalSeats - movieTime.availableSeats
        if (movieTime.isSelected) {
            movieTimeHolder.progressBar.progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary))
            movieTimeHolder.movieTimeTV.setTextColor(context.resources.getColor(android.R.color.black))
            movieTimeHolder.movieTimeTV.typeface = bold
        } else {
            movieTimeHolder.progressBar.progressTintList = ColorStateList.valueOf(context.resources.getColor(android.R.color.tab_indicator_text))
            movieTimeHolder.movieTimeTV.setTextColor(context.resources.getColor(android.R.color.tab_indicator_text))
            movieTimeHolder.movieTimeTV.typeface = font
        }
        movieTimeHolder.progressBar.setOnClickListener {
            if (selectedpos == position) {
                movieTimeList[position].isSelected = false
                notifyItemChanged(position)
                selectedpos = -1
            } else {
                if (selectedpos >= 0) {
                    movieTimeList[position].isSelected = true
                    movieTimeList[selectedpos].isSelected =  false

                    notifyItemChanged(position)
                    notifyItemChanged(selectedpos)
                    selectedpos = position
                } else {
                    movieTimeList[position].isSelected = true
                    notifyItemChanged(position)
                    selectedpos = position
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return movieTimeList.size
    }

    companion object {
        var selectedpos = -1
        var selectedprogressBar: ProgressBar? = null
    }


}


