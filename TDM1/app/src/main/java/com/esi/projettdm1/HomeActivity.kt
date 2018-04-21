package com.esi.projettdm1

import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager

import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

import com.esi.projettdm1.adapters.MovieAdapter
import com.esi.projettdm1.adapters.SerieAdapter
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Serie
import com.esi.projettdm1.utils.*

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.element_bottom_navigation.view.*

import java.util.ArrayList


public class HomeActivity : AppCompatActivity() {



    internal  var contentViewId: Int = R.layout.activity_home

    internal  var navigationMenuItemId: Int = R.id.navigation_home
    lateinit var regular: Typeface
    lateinit var bold:Typeface



    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger:FontChanger
    lateinit var moviesRV: RecyclerView
    lateinit var navigationView : BottomNavigationView
    lateinit var movieList: MutableList<Movie>
    lateinit var movieAdapter: MovieAdapter
    lateinit var seriesRV: RecyclerView
    lateinit var serieList: MutableList<Serie>
    lateinit var serieAdapter: SerieAdapter
    lateinit var layoutManagerSeries: LinearLayoutManager
    lateinit var layoutManagerMovies : LinearLayoutManager
    lateinit var backdropIV: ImageView
    lateinit var snapHelperMovies: SnapHelper
    lateinit var snapHelperSeries: SnapHelper



    override fun onCreate(savedInstanceState: Bundle?) {
        contentViewId = R.layout.activity_home

        navigationMenuItemId = R.id.navigation_home
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_home)
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

        for (i in 0 until GlobalData.series.size) {
            val genreList = ArrayList<String>()
            genreList.add(GlobalData.seriesGenres[i])
            val serie = Serie()
            serie.title = GlobalData.series[i]
            serie.posterPath = GlobalData.seriesPosters[i]
            serie.overview =  GlobalData.seriesDesc[i]
            serie.genres = genreList
            serieList.add(serie)
            serieAdapter.notifyDataSetChanged()
        }
        Picasso.with(applicationContext).load(movieList[0].posterPath).into(backdropIV)

      //  navigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
      //  navigationView.setOnNavigationItemSelectedListener(this)


        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu:Menu = bottomNavigationView.menu
        var menuItem :MenuItem = menu.getItem(0)
        menuItem.setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
    /*        bottomNavigationView.postDelayed({
                val itemId = item.itemId
                if (itemId == R.id.navigation_home) {
                    startActivity(Intent(this, HomeActivity::class.java))
                }
                finish()
            }, 300)*/
            when (item.itemId) {
                R.id.navigation_home -> {
                }

                R.id.navigation_cinema-> {
                    val intent1 = Intent(this@HomeActivity, CinemaActivity::class.java)
                    startActivity(intent1)
                }

                R.id.navigation_personnes -> {
                    val intent2 = Intent(this@HomeActivity, PersonnesActivity::class.java)
                    startActivity(intent2)
                }

                R.id.navigation_series-> {

                    val intent3 = Intent(this@HomeActivity, SeriesActivity::class.java)
                    startActivity(intent3)
                }

                R.id.navigation_favoris-> {
                    val intent4 = Intent(this@HomeActivity, FavoritesActivity::class.java)
                    startActivity(intent4)

                }

            }


            false
        }
    }

    fun init() {

        //Changing the font throughout the activity

        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)

        ////////////////////////////////////////
        moviesRV = findViewById(R.id.moviesRV)
        seriesRV = findViewById(R.id.seriesRV)
        /////////////////////////////////////////////////////////////////
        movieList = ArrayList<Movie>()
        serieList = ArrayList<Serie>()
        /////////////////////////////////////////////////////////////
        movieAdapter = MovieAdapter(movieList, this@HomeActivity)
        serieAdapter = SerieAdapter(serieList,this@HomeActivity)
        ///////////////////////////////////////////////////////////////
        layoutManagerSeries = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        ///////////////////////////////////////////
        snapHelperMovies = PagerSnapHelper()
        snapHelperSeries = PagerSnapHelper()
        snapHelperMovies.attachToRecyclerView(moviesRV)
        snapHelperSeries.attachToRecyclerView(seriesRV)
        ///////////////////////////////////////////
        moviesRV.layoutManager = layoutManagerMovies
        seriesRV.layoutManager = layoutManagerSeries
        ////////////////////////////////////////////
        moviesRV.addItemDecoration(LinePagerIndicatorDecoration(this@HomeActivity))
        seriesRV.addItemDecoration(LinePagerIndicatorDecoration(this@HomeActivity))
        /////////////////////////////////////////////////////
        moviesRV.adapter = movieAdapter
        seriesRV.adapter = serieAdapter

////////////////////////////////////////////////////////////

        backdropIV = findViewById(R.id.backdropIV)

        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override  fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardChange(middleElement)
            }
        }
        val callbackSerie = object : MiddleItemFinder.MiddleItemCallback {
            override  fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardSerieChange(middleElement)
            }
        }

       moviesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerMovies,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        seriesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerSeries,
                        callbackSerie, RecyclerView.SCROLL_STATE_IDLE))
    }

    fun onActiveCardChange(pos: Int) {

            Picasso.with(applicationContext).load(movieList[pos].posterPath).into(backdropIV)

    }
    fun onActiveCardSerieChange(pos: Int) {

        Picasso.with(applicationContext).load(serieList[pos].posterPath).into(backdropIV)

    }




    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

  /*  override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView.postDelayed({
            val itemId = item.itemId
            Log.d("item",itemId.toString())
            when (itemId) {
                R.id.navigation_home -> {
                    val intent5 = Intent(this@HomeActivity, HomeActivity::class.java)
                    startActivity(intent5)

                }

                R.id.navigation_cinema-> {
                    val intent1 = Intent(this@HomeActivity, CinemaActivity::class.java)
                    startActivity(intent1)
                }

                R.id.navigation_personnes -> {
                    val intent2 = Intent(this@HomeActivity, PersonnesActivity::class.java)
                    startActivity(intent2)
                }

                R.id.navigation_series-> {

                    val intent3 = Intent(this@HomeActivity, SeriesActivity::class.java)
                    startActivity(intent3)
                }

                R.id.navigation_films-> {
                    val intent4 = Intent(this@HomeActivity, MoviesActivity::class.java)
                    startActivity(intent4)

                }
            }
            finish()
        }, 300)
        return true
    }*/

 /*   private fun updateNavigationBarState() {
        val actionId = navigationMenuItemId
        selectBottomNavigationBarItem(actionId)
    }

    internal fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView.menu.findItem(itemId)
        item.isChecked = true
    }*/











}


