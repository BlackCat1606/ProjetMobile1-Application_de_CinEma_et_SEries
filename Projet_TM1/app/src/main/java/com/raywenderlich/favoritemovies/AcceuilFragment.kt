package com.raywenderlich.favoritemovies

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class AcceuilFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {
        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_acceuil, container, false)
        val args = arguments

        return view
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(movie: String): AcceuilFragment {

            // Store the movie data in a Bundle object
            val args = Bundle()
            args.putString("title", movie)
            //args.putInt(MovieHelper.KEY_RATING, movie.rating)
            //args.putString(MovieHelper.KEY_POSTER_URI, movie.posterUri)
            //args.putString(MovieHelper.KEY_OVERVIEW, movie.overview)

            // Create a new MovieFragment and set the Bundle as the arguments
            // to be retrieved and displayed when the view is created
            val fragment = AcceuilFragment()
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
