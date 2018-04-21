package com.esi.projettdm1

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SnapHelper
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.esi.projettdm1.adapters.*

import com.esi.projettdm1.data.*
import com.esi.projettdm1.utils.FontChanger
import com.esi.projettdm1.utils.LinePagerIndicatorDecoration
import com.esi.projettdm1.utils.MiddleItemFinder
import com.esi.projettdm1.utils.Rotate3dAnimation
import com.esi.projettdm1.utils.TransitionHelper
import com.squareup.picasso.Picasso

import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_serie_details.*
import kotlinx.android.synthetic.main.picture_column_item.*
import kotlinx.android.synthetic.main.serie_picture_column_item.*



class  SerieDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold:Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger:FontChanger
    lateinit var serieNameTV: TextView
    lateinit var genreTV:TextView
    lateinit var descTV:TextView
    lateinit var backdropIV: ImageView
    lateinit var sessionLL: LinearLayout
    lateinit var picturesRV: RecyclerView
    lateinit var ptSerieList: MutableList<PTMovie>
    lateinit var ptAdapter: PTAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var snapHelper: SnapHelper
    lateinit var sessionTimeButton: Button
    internal var currentPosition = 0
    internal var currentPosition1 = 0
    internal var currentPosition2 = 0

    internal var pos: Int = 0
    lateinit var serieTimeRV: RecyclerView
    lateinit var serieTimeList: MutableList<MovieTime>
    lateinit var timeSelectionAdapter: TimeSelectionAdapter
    lateinit var detailsLL: LinearLayout
    lateinit var timeSelectionLL:LinearLayout
    internal var timeopen = false
    lateinit var bookTicketsBTN: Button
    lateinit var descCV: CardView



    lateinit var similarSeriesRV : RecyclerView
    lateinit var similarSeriesAdapter : SimilarSeriesAdapter
    lateinit var similarSeriesList : MutableList<Serie>
    lateinit var similarSeriesLayoutManager : LinearLayoutManager


    lateinit var seasons :RecyclerView
    lateinit var seasonsAdapter : SeasonAdapter
    lateinit var seasonsList: MutableList<Saison>
    lateinit var seasonsLayoutManager : LinearLayoutManager


    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        serieNameTV = findViewById(R.id.serieNameTV)
        genreTV = findViewById(R.id.genreTV)
        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)
        sessionLL = findViewById(R.id.sessionLL)
        timeSelectionLL = findViewById(R.id.timeSelectionLL)
        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)
        ptSerieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptSerieList, this@SerieDetailsActivity)
        bookTicketsBTN = findViewById(R.id.bookTicketsBTN)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SerieDetailsActivity))
        picturesRV.adapter = ptAdapter
        picturesRV.post { supportStartPostponedEnterTransition() }



        similarSeriesRV = findViewById(R.id.similarSeries)
        similarSeriesList = ArrayList<Serie>()
        similarSeriesAdapter = SimilarSeriesAdapter(similarSeriesList,this@SerieDetailsActivity)
        similarSeriesLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarSeriesSnapHelper = PagerSnapHelper()
        // similarSeriesSnapHelper.attachToRecyclerView(similarSeriesRV)

        similarSeriesRV.layoutManager = similarSeriesLayoutManager
        similarSeriesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SerieDetailsActivity))
        similarSeriesRV.adapter = similarSeriesAdapter

/////////////////////////////////////////////////////////

        seasons = findViewById(R.id.seasons)
        seasonsList = ArrayList<Saison>()
        seasonsAdapter = SeasonAdapter(seasonsList, this@SerieDetailsActivity)
        seasonsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(castMovies)

        seasons.layoutManager = seasonsLayoutManager
        seasons.addItemDecoration(LinePagerIndicatorDecoration(this@SerieDetailsActivity))
        seasons.adapter = seasonsAdapter
////////////////////////////////////////////////////////////


        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition = middleElement

            }
        }
        val callback1 = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition1 = middleElement

            }
        }
        val callback2 = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition2 = middleElement

            }
        }
        picturesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        picturesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ///////////////////////////////////////////////////////////////////////////////////////////
        similarSeriesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, similarSeriesLayoutManager,
                        callback1, RecyclerView.SCROLL_STATE_IDLE))

        similarSeriesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })


        seasons.addOnScrollListener(
                MiddleItemFinder(applicationContext, seasonsLayoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        seasons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })

        serieTimeRV = findViewById(R.id.serieTimeRV)
        serieTimeList = ArrayList<MovieTime>()
        timeSelectionAdapter = TimeSelectionAdapter(serieTimeList, this@SerieDetailsActivity)
        val layoutManager1 = LinearLayoutManager(this@SerieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        serieTimeRV.layoutManager = layoutManager1
        serieTimeRV.adapter = timeSelectionAdapter

        serieTimeList.add(MovieTime("7:00 am", 300, 250))
        serieTimeList.add(MovieTime("11:00 am", 300, 120))
        serieTimeList.add(MovieTime("3:00 pm", 300, 60))
        serieTimeList.add(MovieTime("6:45 pm", 300, 50))
        serieTimeList.add(MovieTime("10:00 pm", 300, 170))
        timeSelectionAdapter.notifyDataSetChanged()

    }



    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_serie_details)
        init()

        pos = intent.getIntExtra("pos", 0)

        for (i in 0 until GlobalData.series.size) {
            val genreList = ArrayList<String>()
            genreList.add(GlobalData.seriesGenres[i])
            val serie = Serie()
            serie.title = GlobalData.series[i]
            serie.posterPath = GlobalData.seriesPosters[i]
            serie.overview =  GlobalData.seriesDesc[i]
            serie.genres = genreList
            similarSeriesList.add(serie)
            similarSeriesAdapter.notifyDataSetChanged()
        }

        when (pos) {
            0 -> {

                for (i in 0 until GlobalData.GOTseasons.size) {

                    val season = Saison()
                    season.title = GlobalData.GOTseasons[i]
                    season.posterPath = GlobalData.GOTseasonsPosters[i]
                    season.overview =  GlobalData.GOTseasonsDescs[i]
                    season.seasonNumber = GlobalData.GOTseasonsNumber[i]
                    seasonsList.add(season)
                    seasonsAdapter.notifyDataSetChanged()
                }


            }
            1 -> {
                for (i in 0 until GlobalData.DHseasons.size) {


                    val season = Saison()
                    season.title = GlobalData.DHseasons[i]
                    season.posterPath = GlobalData.DHseasonsPosters[i]
                    season.overview =  GlobalData.DHseasonsDescs[i]
                    season.seasonNumber = GlobalData.DHseasonsNumber[i]
                    seasonsList.add(season)
                    seasonsAdapter.notifyDataSetChanged()
                }
            }
            2 -> {

                for (i in 0 until GlobalData.BLseasons.size) {


                    val season = Saison()
                    season.title = GlobalData.BLseasons[i]
                    season.posterPath = GlobalData.BLseasonsPosters[i]
                    season.overview =  GlobalData.BLseasonsDescs[i]
                    season.seasonNumber = GlobalData.BLseasonsNumber[i]
                    seasonsList.add(season)
                    seasonsAdapter.notifyDataSetChanged()
                }
            }
            3 -> {
                for (i in 0 until GlobalData.BBseasons.size) {


                    val season = Saison()
                    season.title = GlobalData.BBseasons[i]
                    season.posterPath = GlobalData.BBseasonsPosters[i]
                    season.overview =  GlobalData.BBseasonsDescs[i]
                    season.seasonNumber = GlobalData.BBseasonsNumber[i]
                    seasonsList.add(season)
                    seasonsAdapter.notifyDataSetChanged()
                }
            }
        }




        pos = intent.getIntExtra("pos", 0)
        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)
        serieNameTV.setText(GlobalData.series[pos])
        genreTV.setText(GlobalData.seriesGenres[pos])
        descTV.setText(GlobalData.seriesDesc[pos])
        Picasso.with(applicationContext).load(GlobalData.seriesPosters[pos]).into(backdropIV)
        ptSerieList.add(PTMovie("Picture", GlobalData.seriesPosters[pos]))
        ptSerieList.add(PTMovie("Video", GlobalData.seriesVideos[pos]))
        ptAdapter.notifyDataSetChanged()
        window.sharedElementEnterTransition = TransitionInflater.from(applicationContext).inflateTransition(R.transition.detail_activity_enter_transition)
        val handler = Handler()

        handler.postDelayed({
            descTV.animate().translationY(sessionLL.height.toFloat()).setDuration(500).setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animator: Animator) {

                }

                override fun onAnimationEnd(animator: Animator) {
                    sessionLL.visibility = View.VISIBLE
                }

                override fun onAnimationCancel(animator: Animator) {

                }

                override fun onAnimationRepeat(animator: Animator) {

                }
            }).start()
        }, 300)

        sessionTimeButton.setOnClickListener {
            detailsLL.animate().alpha(0f).duration = 500
            val handler1 = Handler()
            handler1.postDelayed({
                detailsLL.visibility = View.GONE
                timeSelectionLL.visibility = View.VISIBLE
                timeopen = true
            }, 500)
        }

        bookTicketsBTN.setOnClickListener {
            val intent = Intent(this@SerieDetailsActivity, CommentsActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("Commenttype","serie")
            intent.putExtra("pos1", ptSerieList[currentPosition].type)

            if (ptSerieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV)  , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SerieDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV) , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SerieDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            }
        }

    }



    override fun onBackPressed() {


        if (timeopen) {
            println("TESTING " + timeopen)
            timeSelectionLL.animate().alpha(0f).duration = 500


            val handler = Handler()
            handler.postDelayed({
                timeSelectionLL.visibility = View.GONE
                detailsLL.visibility = View.VISIBLE
                timeopen = false
            }, 500)
        } else {
            if (!timeopen) {
                println("Testing Hello")
                sessionLL.visibility = View.GONE
                supportFinishAfterTransition()
                super.onBackPressed()
            }
        }

    }
}
