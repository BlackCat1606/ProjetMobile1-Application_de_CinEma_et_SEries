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
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView

import com.esi.projettdm1.MovieDetailsActivity
import com.esi.projettdm1.R
import com.esi.projettdm1.data.PTMovie
import com.esi.projettdm1.utils.FontChanger
import com.squareup.picasso.Picasso

import android.content.ContentValues.TAG


class PTAdapter(private val ptMovieList: List<PTMovie>, internal var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun getCurrentProgress(): Int {
        return currentProgress
    }

    inner class PictureViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var pictureIV: ImageView

        init {

            pictureIV = view.findViewById(R.id.pictureIV)
            /*Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/product_san_regular.ttf");
            Typeface bold = Typeface.createFromAsset(context.getAssets(),"fonts/product_sans_bold.ttf");
            FontChanger regularFontChanger = new FontChanger(font);
            regularFontChanger.replaceFonts((ViewGroup)view);*/

        }
    }

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var videoLL: LinearLayout

        init {

            videoLL = view.findViewById(R.id.videoLL)
            val font = Typeface.createFromAsset(context.assets, "fonts/product_san_regular.ttf")
            val bold = Typeface.createFromAsset(context.assets, "fonts/product_sans_bold.ttf")
            val regularFontChanger = FontChanger(font)
            regularFontChanger.replaceFonts(view as ViewGroup)

        }
    }


    override fun getItemViewType(position: Int): Int {

        return if (ptMovieList[position].type.equals("Picture")) {
            1
        } else {
            2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {

            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.picture_column_item, parent, false)
            return PictureViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.video_column_item, parent, false)
            return VideoViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        val pt = ptMovieList[position]


        if (holder is PictureViewHolder) {
            Picasso.with(context).load(pt.url).into(holder.pictureIV)
        } else {
            val holder1 = holder as VideoViewHolder
            val videoPlayer = VideoPlayer(context)

            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            videoPlayer.setLayoutParams(params)
            videoPlayer.setScaleType(VideoPlayer.ScaleType.CENTER_CROP)
            holder1.videoLL.addView(videoPlayer)
            videoPlayer.loadVideo(pt.url, pt)
            currentProgress = videoPlayer.currentProgress
        }

    }

    override fun getItemCount(): Int {
        return ptMovieList.size
    }

    companion object {
        var currentProgress = 0
    }


}


