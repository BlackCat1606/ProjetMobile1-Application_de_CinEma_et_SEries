/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.alltherecipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList
import java.util.HashMap
import com.squareup.picasso.Picasso
import android.graphics.Typeface
import com.raywenderlich.favoritemovies.R

class FilmAdapter(private val mContext: Context, private val mDataSource: ArrayList<Int>) : BaseAdapter() {
    private val mInflater: LayoutInflater


    init {
        mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun getCount(): Int {
        return mDataSource.size
    }


    override fun getItem(position: Int): Any {
        return mDataSource[position]
    }


    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        val holder: ViewHolder

        // check if the view already exists if so, no need to inflate and findViewById again!
        if (convertView == null) {

            // Inflate the custom row layout from your XML.
            convertView = mInflater.inflate(R.layout.film_item, parent, false)

            // create a new "Holder" with subviews
            holder = ViewHolder()
            holder.thumbnailImageView = convertView!!.findViewById<ImageView>(R.id.recipe_list_thumbnail)
            holder.titleTextView = convertView.findViewById<TextView>(R.id.recipe_list_title)
            holder.subtitleTextView = convertView.findViewById<TextView>(R.id.recipe_list_subtitle)
            holder.detailTextView = convertView.findViewById<TextView>(R.id.recipe_list_detail)

            // hang onto this holder for future recyclage
            convertView.tag = holder
        } else {

            // skip all the expensive inflation/findViewById and just get the holder you already made
            holder = convertView.tag as ViewHolder
        }

        // Get relevant subviews of row view
        val titleTextView = holder.titleTextView
        val subtitleTextView = holder.subtitleTextView
        val detailTextView = holder.detailTextView
        val thumbnailImageView = holder.thumbnailImageView

        //Get corresponding recipe for row
        val recipe = getItem(position) //as Recipe

        // Update row view's textviews to display recipe information
        titleTextView!!.setText("salam"/*recipe.title*/)
        subtitleTextView!!.setText(/*recipe.description*/"salm")
        detailTextView!!.setText("salm"/*recipe.label*/)

        // Use Picasso to load the image. Temporarily have a placeholder in case it's slow to load
        /*Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap
                .ic_launcher).into(thumbnailImageView)*/
        thumbnailImageView?.setBackgroundResource(R.drawable.blackwater)

        // Style text views
        /*val titleTypeFace = Typeface.createFromAsset(mContext.assets,
                "fonts/JosefinSans-Bold.ttf")
        titleTextView.typeface = titleTypeFace
        val subtitleTypeFace = Typeface.createFromAsset(mContext.assets,
                "fonts/JosefinSans-SemiBoldItalic.ttf")
        subtitleTextView.typeface = subtitleTypeFace
        val detailTypeFace = Typeface.createFromAsset(mContext.assets,
                "fonts/Quicksand-Bold.otf")
        detailTextView.typeface = detailTypeFace
        detailTextView.setTextColor(android.support.v4.content.ContextCompat.getColor(mContext, LABEL_COLORS[recipe.label]))*/

        return convertView
    }

    private class ViewHolder {
        var titleTextView: TextView? = null
        var subtitleTextView: TextView? = null
        var detailTextView: TextView? = null
        var thumbnailImageView: ImageView? = null
    }

    companion object {

        val TAG = FilmAdapter::class.java.simpleName
        val LABEL_COLORS: HashMap<String, Int> = object : HashMap<String, Int>() {
            init {
                put("Low-Carb", R.color.red)
                put("Low-Fat", R.color.red)
                put("Low-Sodium", R.color.red)
                put("Medium-Carb", R.color.red)
                put("Vegetarian", R.color.red)
                put("Balanced", R.color.red)
            }
        }
    }
}
