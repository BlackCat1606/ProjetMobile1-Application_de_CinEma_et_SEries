package com.esi.projettdm1.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.esi.projettdm1.R
import com.esi.projettdm1.data.MovieTime

class SerieTimeSelectionAdapter(private val serieTimeList: List<MovieTime>, internal var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class SerieTimeHolder(view: View) : RecyclerView.ViewHolder(view) {

        var serieTimeTV: TextView
        var progressBar: ProgressBar

        init {

            serieTimeTV = view.findViewById(R.id.serieTimeTV)
            progressBar = view.findViewById(R.id.seatsProgress)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.serie_time_column_item, parent, false)
        return SerieTimeHolder(itemView)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val serieTime = serieTimeList[position]
        val serieTimeHolder = holder as SerieTimeHolder
        serieTimeHolder.serieTimeTV.setText(serieTime.time)
        serieTimeHolder.progressBar.max = serieTime.totalSeats

        val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
        val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")

        serieTimeHolder.serieTimeTV.typeface = font

        serieTimeHolder.progressBar.progress = serieTime.totalSeats - serieTime.availableSeats
        if (serieTime.isSelected) {
            serieTimeHolder.progressBar.progressTintList = ColorStateList.valueOf(context.resources.getColor(R.color.colorPrimary))
            serieTimeHolder.serieTimeTV.setTextColor(context.resources.getColor(android.R.color.black))
            serieTimeHolder.serieTimeTV.typeface = bold
        } else {
            serieTimeHolder.progressBar.progressTintList = ColorStateList.valueOf(context.resources.getColor(android.R.color.tab_indicator_text))
            serieTimeHolder.serieTimeTV.setTextColor(context.resources.getColor(android.R.color.tab_indicator_text))
            serieTimeHolder.serieTimeTV.typeface = font
        }
        serieTimeHolder.progressBar.setOnClickListener {
            if (selectedpos == position) {
                serieTimeList[position].isSelected = false
                notifyItemChanged(position)
                selectedpos = -1
            } else {
                if (selectedpos >= 0) {
                    serieTimeList[position].isSelected = true
                    serieTimeList[selectedpos].isSelected =  false

                    notifyItemChanged(position)
                    notifyItemChanged(selectedpos)
                    selectedpos = position
                } else {
                    serieTimeList[position].isSelected = true
                    notifyItemChanged(position)
                    selectedpos = position
                }
            }
        }


    }

    override fun getItemCount(): Int {
        return serieTimeList.size
    }

    companion object {
        var selectedpos = -1
        var selectedprogressBar: ProgressBar? = null
    }


}