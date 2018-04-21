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
import com.esi.projettdm1.adapters.SerieAdapter
import com.esi.projettdm1.adapters.SerieListAdapter
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Serie
import com.esi.projettdm1.utils.*
import com.squareup.picasso.Picasso
import java.util.ArrayList

class SeriesActivity() :AppCompatActivity() {


    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger

    lateinit var seriesRV: RecyclerView
    lateinit var serieList: MutableList<Serie>
    lateinit var serieAdapter: SerieListAdapter
    lateinit var layoutManagerSeries: LinearLayoutManager

    lateinit var backdropIV: ImageView
    lateinit var snapHelperSeries: SnapHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_series)
        init()
        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)


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
        Picasso.with(applicationContext).load(serieList[0].posterPath).into(backdropIV)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu:Menu = bottomNavigationView.menu
        var menuItem :MenuItem = menu.getItem(3)
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
                    val intent2 = Intent(this@SeriesActivity, HomeActivity::class.java)
                    startActivity(intent2)
                }

                R.id.navigation_cinema-> {
                    val intent1 = Intent(this@SeriesActivity, CinemaActivity::class.java)
                    startActivity(intent1)
                }

                R.id.navigation_personnes -> {
                    val intent3 = Intent(this@SeriesActivity, PersonnesActivity::class.java)
                    startActivity(intent3)

                }

                R.id.navigation_series-> {


                }

                R.id.navigation_films-> {
                    val intent4 = Intent(this@SeriesActivity, MoviesActivity::class.java)
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
        seriesRV = findViewById(R.id.seriesRV)
        /////////////////////////////////////////////////////////////////
        serieList = ArrayList<Serie>()
        /////////////////////////////////////////////////////////////
        serieAdapter = SerieListAdapter(serieList, this@SeriesActivity)
        ///////////////////////////////////////////////////////////////
        layoutManagerSeries = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        ///////////////////////////////////////////
      //  snapHelperSeries = PagerSnapHelper()
    //    snapHelperSeries.attachToRecyclerView(seriesRV)
        ///////////////////////////////////////////
        seriesRV.layoutManager = layoutManagerSeries
        ////////////////////////////////////////////
        seriesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SeriesActivity))
        /////////////////////////////////////////////////////
        seriesRV.adapter = serieAdapter

////////////////////////////////////////////////////////////

        backdropIV = findViewById(R.id.backdropIV)


        val callbackSerie = object : MiddleItemFinder.MiddleItemCallback {
            override  fun scrollFinished(middleElement: Int) {
                // interaction with middle item

                onActiveCardSerieChange(middleElement)
            }
        }



        seriesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManagerSeries,
                        callbackSerie, RecyclerView.SCROLL_STATE_IDLE))
    }

    fun onActiveCardSerieChange(pos: Int) {

        Picasso.with(applicationContext).load(serieList[pos].posterPath).into(backdropIV)

    }


}