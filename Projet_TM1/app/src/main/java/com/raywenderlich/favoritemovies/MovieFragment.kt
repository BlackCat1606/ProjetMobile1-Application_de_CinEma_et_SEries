/*
 * Copyright (c) 2017 Razeware LLC
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
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.favoritemovies

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.squareup.picasso.Picasso
import android.support.v7.widget.RecyclerView



class MovieFragment : Fragment() {
  private var adapter: MyRecyclerViewAdapter? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
  Bundle?): View? {

    // Creates the view controlled by the fragment
    val view = inflater.inflate(R.layout.fragment_movie, container, false)
    val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

    // data to populate the RecyclerView with
    val viewColoers = ArrayList<Int>()
    viewColoers.add(Color.BLACK)
    viewColoers.add(Color.YELLOW)
    viewColoers.add(Color.MAGENTA)
    viewColoers.add(Color.RED)
    viewColoers.add(Color.BLACK)

    val animalNames = ArrayList<String>()
    animalNames.add("Horse")
    animalNames.add("Cow")
    animalNames.add("Camel")
    animalNames.add("Sheep")
    animalNames.add("Goat")
    // set up the RecyclerView
    val recyclerView:RecyclerView = view.findViewById(R.id.rvAnimals)
    val horizontalLayoutManagaer = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
    recyclerView.layoutManager = horizontalLayoutManagaer
    adapter = MyRecyclerViewAdapter(this.context, viewColoers, animalNames)
    //adapter!!.setClickListener()
    recyclerView.adapter = adapter

    /*val ratingTextView = view.findViewById<TextView>(R.id.ratingTextView)
    val posterImageView = view.findViewById<ImageView>(R.id.posterImageView)
    val overviewTextView = view.findViewById<TextView>(R.id.overviewTextView)*/

    // Retrieve and display the movie data from the Bundle
    val args = arguments
    titleTextView.text = args.getString(MovieHelper.KEY_TITLE)
    //ratingTextView.text = String.format("%d/10", args.getInt(MovieHelper.KEY_RATING))
    //overviewTextView.text = args.getString(MovieHelper.KEY_OVERVIEW)

    // Download the image and display it using Picasso
    /*Picasso.with(activity)
        .load(resources.getIdentifier(args.getString(MovieHelper.KEY_POSTER_URI), "drawable", activity.packageName))
        .into(posterImageView)*/

    return view
  }

  companion object {

    // Method for creating new instances of the fragment
    fun newInstance(movie: String): MovieFragment {

      // Store the movie data in a Bundle object
      val args = Bundle()
      args.putString(MovieHelper.KEY_TITLE, movie)
      //args.putInt(MovieHelper.KEY_RATING, movie.rating)
      //args.putString(MovieHelper.KEY_POSTER_URI, movie.posterUri)
      //args.putString(MovieHelper.KEY_OVERVIEW, movie.overview)

      // Create a new MovieFragment and set the Bundle as the arguments
      // to be retrieved and displayed when the view is created
      val fragment = MovieFragment()
      fragment.arguments = args
      return fragment
    }
  }

}
