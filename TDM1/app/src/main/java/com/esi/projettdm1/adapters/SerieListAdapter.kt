package com.esi.projettdm1.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.esi.projettdm1.SerieDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.data.Serie
import com.esi.projettdm1.utils.FontChanger
import com.squareup.picasso.Picasso

class SerieListAdapter(private val serieList: List<Serie>, internal var context: Context) : RecyclerView.Adapter<SerieListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var serieNameTV: TextView
        var genreTV: TextView



        var serieDetailsCV: CardView
        var seriePosterCardCV: CardView
        var posterIV: ImageView

        init {

            serieNameTV = view.findViewById(R.id.serieNameTV)
            genreTV = view.findViewById(R.id.genreTV)

            posterIV = view.findViewById(R.id.seriePosterIV)


            serieDetailsCV = view.findViewById(R.id.serieDetailCard)
            seriePosterCardCV = view.findViewById(R.id.seriePosterCard)

            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.serie_column_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val serie = serieList[position]

        holder.serieNameTV.setText(serie.title)
        var genres = ""
        for (i in 0 until serie.genres!!.size) {
            genres = genres + " " + serie.genres!!.get(i)
        }
        holder.genreTV.text = genres

        Picasso.with(context).load(serie.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, SerieDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.seriePosterCardCV as View, holder.seriePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.serieDetailsCV as View, holder.serieDetailsCV.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }

        holder.serieDetailsCV.setOnClickListener {
            val intent = Intent(context, SerieDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.seriePosterCardCV as View, holder.seriePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.serieDetailsCV as View, holder.serieDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return serieList.size
    }


}

