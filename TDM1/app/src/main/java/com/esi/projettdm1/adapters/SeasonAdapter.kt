package com.esi.projettdm1.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

import com.esi.projettdm1.R
import com.esi.projettdm1.SeasonDetailsActivity
import com.esi.projettdm1.data.Saison
import com.esi.projettdm1.utils.FontChanger


class SeasonAdapter(private val seasonList: List<Saison>, internal var context: Context) : RecyclerView.Adapter<SeasonAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var seasonPosterCardCV: CardView
        var posterIV: ImageView
        var seasonDetailsCV : CardView
        var seasonNumber : TextView

        init {


            posterIV = view.findViewById(R.id.seasonPoster)

            seasonPosterCardCV = view.findViewById(R.id.seasonPosterCard)
            seasonDetailsCV = view.findViewById(R.id.seasonDetailCard)
            seasonNumber = view.findViewById(R.id.seasonNb)
            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.season_image_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val season = seasonList[position]


        holder.seasonNumber.setText(season.seasonNumber)
        if (season.posterPath != null) {
            Log.d("TAG", season.posterPath.toString())
        } else {
            Log.d("Hi","By")
        }
        Log.d("SeasonTitle",season.title)

        Picasso.with(context).load(season.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, SeasonDetailsActivity::class.java)

            intent.putExtra("seriePos", position)
            intent.putExtra("serieNom",season.title)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.seasonPosterCardCV as View, holder.seasonPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.seasonDetailsCV as View, holder.seasonDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
        holder.seasonDetailsCV.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, SeasonDetailsActivity::class.java)
            intent.putExtra("seriePos", position)
            intent.putExtra("serieNom",season.title)
            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.seasonPosterCardCV as View, holder.seasonPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create<View, String>(holder.seasonDetailsCV as View, holder.seasonDetailsCV.getTransitionName())


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        })

    }

    override fun getItemCount(): Int {
        return seasonList.size
    }


}


