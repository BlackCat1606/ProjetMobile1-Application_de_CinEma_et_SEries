package com.esi.projettdm1

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.view.*
import android.widget.ImageView
import com.esi.projettdm1.adapters.MovieAdapter
import com.esi.projettdm1.adapters.MovieListAdapter
import com.esi.projettdm1.adapters.SerieAdapter
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Serie
import com.esi.projettdm1.utils.*
import com.squareup.picasso.Picasso
import java.util.ArrayList

class MoviesActivity() :AppCompatActivity() {


    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger

    lateinit var moviesRV: RecyclerView

    lateinit var movieList: MutableList<Movie>
    lateinit var movieAdapter: MovieListAdapter

    lateinit var layoutManagerMovies : LinearLayoutManager
    lateinit var backdropIV: ImageView
    lateinit var snapHelperMovies: SnapHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_movies)
        init()
        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)
          for (i in 0 until GlobalData.movies.size) {
            val genreList = ArrayList<String>()
            genreList.add(GlobalData.genres[i])
            val movie = Movie()
            movie.originalTitle = GlobalData.movies[i]
            movie.posterPath = GlobalData.posters[i]
            movie.overview =  GlobalData.desc[i]
            movie.genres = genreList
            movieList.add(movie)
            movieAdapter.notifyDataSetChanged()
        }


        Picasso.with(applicationContext).load(movieList[0].posterPath).into(backdropIV)


    /*    var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavViewBar) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu: Menu = bottomNavigationView.menu
        var menuItem : MenuItem = menu.getItem(0)
        menuItem.setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_house -> {
                    val intent1 = Intent(this@MoviesActivity, HomeActivity::class.java)
                    startActivity(intent1)
                }


                R.id.ic_search -> {
                    val intent1 = Intent(this@MoviesActivity, AcceuilActivity::class.java)
                    startActivity(intent1)
                }

                R.id.ic_circle -> {

                }

                R.id.ic_alert -> {

                }

                R.id.ic_android -> {

                }
            }


            false
        }*/

    }

    fun init() {

        //Changing the font throughout the activity


        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)

        ////////////////////////////////////////
        moviesRV = findViewById(R.id.moviesRV)

        /////////////////////////////////////////////////////////////////
        movieList = ArrayList<Movie>()

        /////////////////////////////////////////////////////////////
        movieAdapter = MovieListAdapter(movieList, this@MoviesActivity)

        ///////////////////////////////////////////////////////////////
        layoutManagerMovies = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ///////////////////////////////////////////
  //      snapHelperMovies = PagerSnapHelper()
//        snapHelperMovies.attachToRecyclerView(moviesRV)
        ///////////////////////////////////////////
        moviesRV.layoutManager = layoutManagerMovies
        ////////////////////////////////////////////
        moviesRV.addItemDecoration(LinePagerIndicatorDecoration(this@MoviesActivity))
        /////////////////////////////////////////////////////
        moviesRV.adapter = movieAdapter

////////////////////////////////////////////////////////////

        backdropIV = findViewById(R.id.backdropIV)

        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardChange(middleElement)
            }
        }


        moviesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerMovies,
                        callback, RecyclerView.SCROLL_STATE_IDLE))
    }


    fun onActiveCardChange(pos: Int) {

            Picasso.with(applicationContext).load(movieList[pos].posterPath).into(backdropIV)

    }



}