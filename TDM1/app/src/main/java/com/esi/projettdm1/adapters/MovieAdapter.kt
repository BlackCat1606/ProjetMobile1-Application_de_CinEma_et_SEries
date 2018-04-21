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
import com.squareup.picasso.Picasso

import com.esi.projettdm1.MovieDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.data .Movie
import com.esi.projettdm1.utils.FontChanger



class MovieAdapter(private val movieList: List<Movie>, internal var context: Context) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var moviePosterCardCV: CardView
        var posterIV: ImageView

        init {


            posterIV = view.findViewById(R.id.moviePosterIV)

            moviePosterCardCV = view.findViewById(R.id.moviePosterCard)

            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_image_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movie = movieList[position]


        Picasso.with(context).load(movie.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(1)


            transitionPairs[0] = Pair.create(holder.moviePosterCardCV as View, holder.moviePosterCardCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }

    }

    override fun getItemCount(): Int {
        return movieList.size
    }


}


