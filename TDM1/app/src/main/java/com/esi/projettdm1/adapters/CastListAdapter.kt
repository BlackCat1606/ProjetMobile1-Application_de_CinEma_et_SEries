

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
import com.esi.projettdm1.CastDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.data.Personne
import com.esi.projettdm1.utils.FontChanger
import com.squareup.picasso.Picasso

class CastListAdapter(private val castList: List<Personne>, internal var context: Context) : RecyclerView.Adapter<CastListAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var castNameTV: TextView




        var castDetailsCV: CardView
        var castPosterCardCV: CardView
        var posterIV: ImageView

        init {

            castNameTV = view.findViewById(R.id.castName)


            posterIV = view.findViewById(R.id.castPosterIV)


            castDetailsCV = view.findViewById(R.id.castDetailCard)
            castPosterCardCV = view.findViewById(R.id.castPosterCard)

            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.cast_column_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cast = castList[position]

        holder.castNameTV.setText(cast.nom)



        Picasso.with(context).load(cast.posterPath).into(holder.posterIV)

        holder.posterIV.setOnClickListener {
            val intent = Intent(context, CastDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)


            transitionPairs[0] = Pair.create(holder.castPosterCardCV as View, holder.castPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.castDetailsCV as View, holder.castDetailsCV.transitionName)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }

        holder.castDetailsCV.setOnClickListener {
            val intent = Intent(context, CastDetailsActivity::class.java)
            intent.putExtra("pos", position)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            transitionPairs[0] = Pair.create(holder.castPosterCardCV as View, holder.castPosterCardCV.transitionName)
            transitionPairs[1] = Pair.create(holder.castDetailsCV as View, holder.castDetailsCV.transitionName)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, *transitionPairs)
            context.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return castList.size
    }


}

