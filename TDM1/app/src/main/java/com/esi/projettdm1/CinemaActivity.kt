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
import com.esi.projettdm1.adapters.*
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Salle
import com.esi.projettdm1.data.Serie
import com.esi.projettdm1.utils.*
import com.squareup.picasso.Picasso
import java.util.ArrayList

public class CinemaActivity : AppCompatActivity() {



    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger

    lateinit var moviesRV: RecyclerView

    lateinit var movieList: MutableList<Movie>
    lateinit var movieAdapter: SimilarMoviesAdapter

    lateinit var layoutManagerMovies : LinearLayoutManager
    lateinit var backdropIV: ImageView
    lateinit var snapHelperMovies: SnapHelper


    lateinit var sallesRV: RecyclerView

    lateinit var sallesList: MutableList<Salle>
    lateinit var sallesAdapter: SalleListAdapter

    lateinit var layoutManagerSalles : LinearLayoutManager

    lateinit var snapHelperSalles: SnapHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_cinema)
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

        for (i in 0 until GlobalData.salles.size) {

            val salle = Salle()
            salle.name = GlobalData.salles[i]
            salle.posterPath = GlobalData.sallesPosters[i]
            salle.movie =  GlobalData.sallesMovies[i]
            sallesList.add(salle)
            sallesAdapter.notifyDataSetChanged()
        }


        Picasso.with(applicationContext).load(movieList[0].posterPath).into(backdropIV)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu:Menu = bottomNavigationView.menu
        var menuItem :MenuItem = menu.getItem(1)
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
                    val intent2 = Intent(this@CinemaActivity, HomeActivity::class.java)
                    startActivity(intent2)
                }

                R.id.navigation_cinema-> {

                }

                R.id.navigation_personnes -> {
                    val intent3 = Intent(this@CinemaActivity, PersonnesActivity::class.java)
                    startActivity(intent3)

                }

                R.id.navigation_series-> {

                    val intent4 = Intent(this@CinemaActivity, SeriesActivity::class.java)
                    startActivity(intent4)
                }

                R.id.navigation_films-> {
                    val intent1 = Intent(this@CinemaActivity, MoviesActivity::class.java)
                    startActivity(intent1)


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

        /////////////////////////////////////////////////////////////////
        movieList = ArrayList<Movie>()

        /////////////////////////////////////////////////////////////
        movieAdapter = SimilarMoviesAdapter(movieList, this@CinemaActivity)

        ///////////////////////////////////////////////////////////////
        layoutManagerMovies = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        ///////////////////////////////////////////
        //      snapHelperMovies = PagerSnapHelper()
//        snapHelperMovies.attachToRecyclerView(moviesRV)
        ///////////////////////////////////////////
        moviesRV.layoutManager = layoutManagerMovies
        ////////////////////////////////////////////
        moviesRV.addItemDecoration(LinePagerIndicatorDecoration(this@CinemaActivity))
        /////////////////////////////////////////////////////
        moviesRV.adapter = movieAdapter



        sallesRV = findViewById(R.id.sallesRV)

        /////////////////////////////////////////////////////////////////
        sallesList = ArrayList<Salle>()

        /////////////////////////////////////////////////////////////
        sallesAdapter = SalleListAdapter(sallesList, this@CinemaActivity)

        ///////////////////////////////////////////////////////////////
        layoutManagerSalles = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ///////////////////////////////////////////
        //      snapHelperMovies = PagerSnapHelper()
//        snapHelperMovies.attachToRecyclerView(moviesRV)
        ///////////////////////////////////////////
        sallesRV.layoutManager = layoutManagerSalles
        ////////////////////////////////////////////
        sallesRV.addItemDecoration(LinePagerIndicatorDecoration(this@CinemaActivity))
        /////////////////////////////////////////////////////
        sallesRV.adapter = sallesAdapter

////////////////////////////////////////////////////////////

        backdropIV = findViewById(R.id.backdropIV)

        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardChange(middleElement)
            }
        }
        val callback1 = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardChange(middleElement)
            }
        }


        moviesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerMovies,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        sallesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerMovies,
                        callback1, RecyclerView.SCROLL_STATE_IDLE))
    }


    fun onActiveCardChange(pos: Int) {

        Picasso.with(applicationContext).load(movieList[pos].posterPath).into(backdropIV)

    }



}