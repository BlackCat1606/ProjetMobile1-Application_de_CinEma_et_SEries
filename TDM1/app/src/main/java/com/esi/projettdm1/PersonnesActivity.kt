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
import android.util.Log
import android.view.*
import android.widget.ImageView
import com.esi.projettdm1.adapters.*
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.data.Movie
import com.esi.projettdm1.data.Personne
import com.esi.projettdm1.utils.*
import com.squareup.picasso.Picasso
import java.util.ArrayList

class PersonnesActivity : AppCompatActivity() {




    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger
    internal var pos: Int = 0
    internal var currentPosition = 0
    internal var currentPosition1 = 0

    lateinit var backdropIV: ImageView
    lateinit var navigationView : BottomNavigationView
    lateinit var castRV : RecyclerView
    lateinit var castAdapter : CastListAdapter
    lateinit var castList : MutableList<Personne>
    lateinit var castLayoutManager : LinearLayoutManager

    lateinit var crewRV : RecyclerView
    lateinit var crewAdapter : CrewListAdapter
    lateinit var crewList : MutableList<Personne>
    lateinit var crewLayoutManager : LinearLayoutManager
    lateinit var snapHelper1: SnapHelper
    lateinit var snapHelper2: SnapHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()


        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_persons)
        init()
        pos = intent.getIntExtra("pos", 0)
        Log.d("Position",pos.toString())

        when (pos) {
            0 -> {

                for (i in 0 until GlobalData.cast0.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.cast0[i]
                    cast.posterPath = GlobalData.castPhotos0[i]
                    cast.biographie =  GlobalData.castBio0[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crew0.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crew0[i]
                    crew.posterPath = GlobalData.crewPhotos0[i]
                    crew.biographie =  GlobalData.crewBio0[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            1 -> {

                for (i in 0 until GlobalData.cast1.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.cast1[i]
                    cast.posterPath = GlobalData.castPhotos1[i]
                    cast.biographie =  GlobalData.castBio1[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crew1.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crew1[i]
                    crew.posterPath = GlobalData.crewPhotos1[i]
                    crew.biographie =  GlobalData.crewBio1[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            2 -> {

                for (i in 0 until GlobalData.cast2.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.cast2[i]
                    cast.posterPath = GlobalData.castPhotos2[i]
                    cast.biographie =  GlobalData.castBio2[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crew2.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crew2[i]
                    crew.posterPath = GlobalData.crewPhotos2[i]
                    crew.biographie =  GlobalData.crewBio2[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            3 -> {


                for (i in 0 until GlobalData.cast3.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.cast3[i]
                    cast.posterPath = GlobalData.castPhotos3[i]
                    cast.biographie =  GlobalData.castBio3[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crew3.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crew3[i]
                    crew.posterPath = GlobalData.crewPhotos3[i]
                    crew.biographie =  GlobalData.crewBio3[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }


            }
        }


        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)


        Picasso.with(applicationContext).load(castList[0].posterPath).into(backdropIV)

        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu:Menu = bottomNavigationView.menu
        var menuItem :MenuItem = menu.getItem(2)
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
                    val intent2 = Intent(this@PersonnesActivity, HomeActivity::class.java)
                    startActivity(intent2)
                }

                R.id.navigation_cinema-> {
                    val intent1 = Intent(this@PersonnesActivity, CinemaActivity::class.java)
                    startActivity(intent1)
                }

                R.id.navigation_personnes -> {

                }

                R.id.navigation_series-> {

                    val intent3 = Intent(this@PersonnesActivity, SeriesActivity::class.java)
                    startActivity(intent3)
                }

                R.id.navigation_favoris-> {
                    val intent4 = Intent(this@PersonnesActivity, FavoritesActivity::class.java)
                    startActivity(intent4)

                }

            }


            false
        }

/*
        var bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavViewBar) as BottomNavigationView
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView)
        var menu: Menu = bottomNavigationView.menu
        var menuItem : MenuItem = menu.getItem(0)
        menuItem.setChecked(true)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_house -> {
                    val intent1 = Intent(this@PersonnesActivity, HomeActivity::class.java)
                    startActivity(intent1)
                }


                R.id.ic_search -> {
                    val intent1 = Intent(this@PersonnesActivity, AcceuilActivity::class.java)
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
        }
        */

    }

    fun init() {

        //Changing the font throughout the activity


        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)

        ////////////////////////////////////////
        castRV = findViewById(R.id.castRV)
        castList = ArrayList<Personne>()
        castAdapter = CastListAdapter(castList,this@PersonnesActivity)
        castLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        castRV.layoutManager = castLayoutManager
        castRV.addItemDecoration(LinePagerIndicatorDecoration(this@PersonnesActivity))
        castRV.adapter = castAdapter

        ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        crewRV = findViewById(R.id.crewRV)
        crewList = ArrayList<Personne>()
        crewAdapter = CrewListAdapter(crewList,this@PersonnesActivity)
        crewLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        crewRV.layoutManager = crewLayoutManager
        crewRV.addItemDecoration(LinePagerIndicatorDecoration(this@PersonnesActivity))
        crewRV.adapter = crewAdapter


        snapHelper1 = PagerSnapHelper()
        snapHelper2 = PagerSnapHelper()
        snapHelper1.attachToRecyclerView(castRV)
        snapHelper2.attachToRecyclerView(crewRV)



        ////////////////////////////////////////////////////

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

                onActiveCrewCardChange(middleElement)
            }
        }
        castRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, castLayoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        castRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        crewRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, crewLayoutManager,
                        callback1, RecyclerView.SCROLL_STATE_IDLE))

        crewRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })



    }


    fun onActiveCardChange(pos: Int) {

            Picasso.with(applicationContext).load(castList[pos].posterPath).into(backdropIV)

    }
    fun onActiveCrewCardChange(pos: Int) {

        Picasso.with(applicationContext).load(crewList[pos].posterPath).into(backdropIV)

    }





    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }






}