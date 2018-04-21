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
import com.esi.projettdm1.EpisodeDetailsActivity
import com.squareup.picasso.Picasso

import com.esi.projettdm1.R
import com.esi.projettdm1.data.Episode
import com.esi.projettdm1.utils.FontChanger


class EpisodeAdapter(private val episodeList: List<Episode>, internal var context: Context) : RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var episodePosterCardCV: CardView
        var posterIV: ImageView
        var episodeDetailsCV : CardView
        var episodeNumber : TextView

        init {


            posterIV = view.findViewById(R.id.episodePoster)

            episodePosterCardCV = view.findViewById(R.id.episodePosterCard)
            episodeDetailsCV = view.findViewById(R.id.episodeDetailCard)
            episodeNumber = view.findViewById(R.id.episodeNb)
            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.episode_image_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val episode = episodeList[position]

        holder.episodeNumber.setText(episode.episodeNumber)
        if (episode.posterPath != null) {
            Log.d("TAG", episode.posterPath.toString())
        } else {
            Log.d("Hi","By")
        }

        Picasso.with(context).load(episode.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, EpisodeDetailsActivity::class.java)
            intent.putExtra("saisonPos", position)
            intent.putExtra("episodeTitle",episode.title)
            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.episodePosterCardCV as View, holder.episodePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.episodeDetailsCV as View, holder.episodeDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
        holder.episodeDetailsCV.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, EpisodeDetailsActivity::class.java)
            intent.putExtra("saisonPos", position)
            intent.putExtra("episodeTitle",episode.title)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.episodePosterCardCV as View, holder.episodePosterCardCV.transitionName)
            transitionPairs[1] = Pair.create<View, String>(holder.episodeDetailsCV as View, holder.episodeDetailsCV.getTransitionName())


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        })

    }

    override fun getItemCount(): Int {
        return episodeList.size
    }


}


