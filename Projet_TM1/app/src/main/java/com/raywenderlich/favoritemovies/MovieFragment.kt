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

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
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
import android.widget.ListView
import kotlinx.android.synthetic.*


class MovieFragment : Fragment() {
    var itemname = arrayOf("Salle01", "Salle02", "Salle03", "Salle04", "Salle05", "Salle06")
    private var adapter: MyRecyclerViewAdapter? = null
    private var adapter2: ListeSalleAdapter? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {

        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_movie, container, false)
        //val titleTextView = view.findViewById<TextView>(R.id.titleTextView)

        // data to populate the RecyclerView with
        val coverFilm = ArrayList<Int>()
        coverFilm.add(R.drawable.avatar2)
        coverFilm.add(R.drawable.samson)
        coverFilm.add(R.drawable.thenun)
        coverFilm.add(R.drawable.victorcrowley)
        coverFilm.add(R.drawable.blackwater)
        coverFilm.add(R.drawable.tombraider)
        coverFilm.add(R.drawable.annihilation)
        coverFilm.add(R.drawable.blackpanther)
        coverFilm.add(R.drawable.showmustgoon)
        coverFilm.add(R.drawable.beautyandthebeast)
        val filmNames = ArrayList<String>()
        filmNames.add("Avatar2")
        filmNames.add("Samson")
        filmNames.add("The Nun")
        filmNames.add("Victor Crowley")
        filmNames.add("Black Water")
        filmNames.add("Tombraider")
        filmNames.add("Annihilation")
        filmNames.add("Black Panther")
        filmNames.add("Go On")
        filmNames.add("Beaty")
        // set up the RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.rvAnimals)
        val horizontalLayoutManagaer = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = horizontalLayoutManagaer
        adapter = MyRecyclerViewAdapter(this.context as Context, coverFilm, filmNames)
        recyclerView.adapter = adapter

        //val args = arguments
        //titleTextView.text = args.getString("title")

        var item: List<String> = arrayListOf("One", "Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen")

        val recyclerView2: RecyclerView = view.findViewById(R.id.list)
        val horizontalLayoutManagaer2 = LinearLayoutManager(this.context)
        recyclerView2.layoutManager = horizontalLayoutManagaer2
        adapter2 = ListeSalleAdapter(this.context as Context)
        recyclerView2.adapter = adapter2

        val args = arguments




        return view
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(movie: String): MovieFragment {

            // Store the movie data in a Bundle object
            val args = Bundle()
            args.putString("title", movie)
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
