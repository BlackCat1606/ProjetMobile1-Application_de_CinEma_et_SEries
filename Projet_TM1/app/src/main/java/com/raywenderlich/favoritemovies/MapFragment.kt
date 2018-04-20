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

import com.squareup.picasso.Picasso
import android.support.v7.widget.RecyclerView
import com.google.android.gms.maps.SupportMapFragment
import kotlinx.android.synthetic.*
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment() : Fragment(), OnMapReadyCallback {


    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap?) {
        var lm: LocationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var location: Location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER) as Location
        var longitude: Double = location.getLongitude()
        var latitude: Double = location.getLatitude()
        val sydney = LatLng(55.70, 13.19)
        p0?.addMarker(MarkerOptions().position(sydney)
                .title("Commencer"))?.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        p0?.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,12.0f))
        //p0?.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12.0f))
    }


    var itemname = arrayOf("Salle01", "Salle02", "Salle03", "Salle04", "Salle05", "Salle06")

    @SuppressLint("ResourceAsColor")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
    Bundle?): View? {

        // Creates the view controlled by the fragment
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        //val titleTextView = view.findViewById<TextView>(R.id.titleTextView)
        //titleTextView.setText("Salam")

        /* // setting the map
         var sensorManager = (activity!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                 .inflate(R.layout.fragment_movie, null, false)
         var searchFilm: SearchView = sensorManager.findViewById<SearchView>(R.id.searchFilm)
         //searchFilm.setQuery("Taper un film à rechercher",false)
         //setting up the map
         var mapFragmeny: SupportMapFragment = fragmentManager?.findFragmentById(R.id.map) as SupportMapFragment
         *//*mapFragmeny.getMapAsync(this )*/
        if ((fragmentManager
                ?.findFragmentById(R.id.map) as? SupportMapFragment) != null) {
            Log.d("salam", "dkhal")
            val mapFragment = fragmentManager
                    ?.findFragmentById(R.id.map) as? SupportMapFragment
            mapFragment?.getMapAsync(this)
            val args = arguments
            return view

        }
        Log.d("non", "madkhalch")
        val args = arguments
        return view
    }

    companion object {

        // Method for creating new instances of the fragment
        fun newInstance(movie: String, a: FragmentActivity): MapFragment {
            // Store the movie data in a Bundle object
            val args = Bundle()
            args.putString("title", movie)
            //args.putInt(MovieHelper.KEY_RATING, movie.rating)
            //args.putString(MovieHelper.KEY_POSTER_URI, movie.posterUri)
            //args.putString(MovieHelper.KEY_OVERVIEW, movie.overview)

            // Create a new MovieFragment and set the Bundle as the arguments
            // to be retrieved and displayed when the view is created
            val fragment = MapFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
