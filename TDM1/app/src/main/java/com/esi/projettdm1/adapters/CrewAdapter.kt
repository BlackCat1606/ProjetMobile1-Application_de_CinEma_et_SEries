package com.esi.projettdm1.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.nfc.Tag
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
import com.esi.projettdm1.CrewDetailsActivity
import com.squareup.picasso.Picasso

import com.esi.projettdm1.MovieDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.data .Movie
import com.esi.projettdm1.data.Personne
import com.esi.projettdm1.utils.FontChanger



class CrewAdapter(private val crewList: List<Personne>, internal var context: Context) : RecyclerView.Adapter<CrewAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {


        var crewPosterCardCV: CardView
        var posterIV: ImageView
        var crewDetailsCV : CardView
        var crewName : TextView

        init {


            posterIV = view.findViewById(R.id.crewPoster)

            crewPosterCardCV = view.findViewById(R.id.crewPosterCard)
            crewDetailsCV = view.findViewById(R.id.crewDetailCard)
            crewName = view.findViewById(R.id.crewName)
            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.crew_image_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val crew = crewList[position]

        holder.crewName.setText(crew.nom)
        Log.d("TAG",holder.crewName.text.toString())
        Picasso.with(context).load(crew.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, CrewDetailsActivity::class.java)
            intent.putExtra("posCrew", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.crewPosterCardCV as View, holder.crewPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.crewDetailsCV as View, holder.crewDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
        holder.crewDetailsCV.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, CrewDetailsActivity::class.java)
            intent.putExtra("posCrew", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.crewPosterCardCV as View, holder.crewPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create<View, String>(holder.crewDetailsCV as View, holder.crewDetailsCV.getTransitionName())


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        })

    }

    override fun getItemCount(): Int {
        return crewList.size
    }


}


