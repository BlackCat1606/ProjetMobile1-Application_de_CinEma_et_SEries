package com.esi.projettdm1

import android.animation.Animator
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.*
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.esi.projettdm1.adapters.*
import com.esi.projettdm1.data.*
import com.esi.projettdm1.utils.FontChanger
import com.esi.projettdm1.utils.LinePagerIndicatorDecoration
import com.esi.projettdm1.utils.MiddleItemFinder
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.ArrayList

class CrewDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger
    lateinit var crewName: TextView
    lateinit var genreTV: TextView
    lateinit var descTV: TextView

    lateinit var backdropIV: ImageView
    lateinit var picturesRV: RecyclerView
    lateinit var ptMovieList: MutableList<PTMovie>
    lateinit var ptAdapter: PTAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var snapHelper: SnapHelper
    lateinit var similarMoviesSnapHelper : SnapHelper
    lateinit var sessionTimeButton: Button
    internal var currentPosition = 0
    internal var pos: Int = 0


    lateinit var detailsLL: LinearLayout

    lateinit var bookTicketsBTN: Button
    lateinit var descCV: CardView


    lateinit var  crewBirthDate : TextView
    lateinit var similarMoviesRV : RecyclerView
    lateinit var similarMoviesAdapter : SimilarMoviesAdapter
    lateinit var similarMoviesList : MutableList<Movie>
    lateinit var similarMoviesLayoutManager : LinearLayoutManager

    lateinit var similarSeasons :RecyclerView
    lateinit var similarSeasonsAdapter : SeasonAdapter
    lateinit var similarSeasonsList: MutableList<Saison>
    lateinit var similarSeasonsLayoutManager : LinearLayoutManager



    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        crewName = findViewById(R.id.crewName)
        crewBirthDate = findViewById(R.id.crewBirthDate)

        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)


        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)
        ///////////////////////////////////////////////////////////////////
        similarMoviesRV = findViewById(R.id.crewMovies)
        similarMoviesList = ArrayList<Movie>()
        similarMoviesAdapter = SimilarMoviesAdapter(similarMoviesList, this@CrewDetailsActivity)
        similarMoviesLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(crewMovies)

        similarMoviesRV.layoutManager = similarMoviesLayoutManager
        similarMoviesRV.addItemDecoration(LinePagerIndicatorDecoration(this@CrewDetailsActivity))
        similarMoviesRV.adapter = similarMoviesAdapter

        ///////////////////////////////////////////////////////////////////////////////
        similarSeasons = findViewById(R.id.crewSeasons)
        similarSeasonsList = ArrayList<Saison>()
        similarSeasonsAdapter = SeasonAdapter(similarSeasonsList, this@CrewDetailsActivity)
        similarSeasonsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(crewMovies)

        similarSeasons.layoutManager = similarSeasonsLayoutManager
        similarSeasons.addItemDecoration(LinePagerIndicatorDecoration(this@CrewDetailsActivity))
        similarSeasons.adapter = similarSeasonsAdapter

        ///////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////

        ptMovieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptMovieList, this@CrewDetailsActivity)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@CrewDetailsActivity))
        picturesRV.adapter = ptAdapter
        picturesRV.post { supportStartPostponedEnterTransition() }


        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition = middleElement

            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        similarMoviesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, similarMoviesLayoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        similarMoviesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        similarSeasons.addOnScrollListener(
                MiddleItemFinder(applicationContext, similarSeasonsLayoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        similarSeasons.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////
        picturesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, layoutManager,
                        callback, RecyclerView.SCROLL_STATE_IDLE))

        picturesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })





    }



    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crew_details)
        init()
        pos = intent.getIntExtra("posCrew", 0)
        Log.d("Position", pos.toString())

        when (pos) {
            0 -> {
                for (i in 0 until GlobalData.similarMovies0.size) {
                    val genreList = ArrayList<String>()
                    genreList.add(GlobalData.similarMoviesgenres0[i])
                    val similarMovie = Movie()
                    similarMovie.originalTitle = GlobalData.similarMovies0[i]
                    similarMovie.posterPath = GlobalData.similarMoviesPosters0[i]
                    similarMovie.overview = GlobalData.similarMoviesdesc0[i]
                    similarMovie.genres = genreList
                    similarMoviesList.add(similarMovie)
                    similarMoviesAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.similarSeasons0.size) {

                    val season = Saison()
                    season.title = GlobalData.similarSeasons0[i]
                    season.posterPath = GlobalData.similarSeasonsPosters0[i]
                    season.overview =  GlobalData.similarSeasonsDesc0[i]
                    season.seasonNumber = GlobalData.similarSeasonsNumber0[i]
                    similarSeasonsList.add(season)
                    similarSeasonsAdapter.notifyDataSetChanged()
                }

                crewName.setText(GlobalData.crew0[pos])
                crewBirthDate.setText(GlobalData.crewBirthDates[pos])
                descTV.setText(GlobalData.crewBio0[pos])
                Picasso.with(applicationContext).load(GlobalData.crewPhotos0[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.crewPhotos0[pos]))



            }
            1 -> {
                for (i in 0 until GlobalData.similarMovies1.size) {
                    val genreList = ArrayList<String>()
                    genreList.add(GlobalData.similarMoviesgenres1[i])
                    val similarMovie = Movie()
                    similarMovie.originalTitle = GlobalData.similarMovies1[i]
                    similarMovie.posterPath = GlobalData.similarMoviesPosters1[i]
                    similarMovie.overview = GlobalData.similarMoviesdesc1[i]
                    similarMovie.genres = genreList
                    similarMoviesList.add(similarMovie)
                    similarMoviesAdapter.notifyDataSetChanged()
                }

                for (i in 0 until GlobalData.similarSeasons1.size) {

                    val season = Saison()
                    season.title = GlobalData.similarSeasons1[i]
                    season.posterPath = GlobalData.similarSeasonsPosters1[i]
                    season.overview =  GlobalData.similarSeasonsDesc1[i]
                    season.seasonNumber = GlobalData.similarSeasonsNumber1[i]
                    similarSeasonsList.add(season)
                    similarSeasonsAdapter.notifyDataSetChanged()
                }

                crewName.setText(GlobalData.crew1[pos])
                crewBirthDate.setText(GlobalData.crewBirthDates[pos])

                descTV.setText(GlobalData.crewBio1[pos])
                Picasso.with(applicationContext).load(GlobalData.crewPhotos1[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.crewPhotos1[pos]))

            }
            2 -> {
                for (i in 0 until GlobalData.similarMovies2.size) {
                    val genreList = ArrayList<String>()
                    genreList.add(GlobalData.similarMoviesgenres2[i])
                    val similarMovie = Movie()
                    similarMovie.originalTitle = GlobalData.similarMovies2[i]
                    similarMovie.posterPath = GlobalData.similarMoviesPosters2[i]
                    similarMovie.overview = GlobalData.similarMoviesdesc2[i]
                    similarMovie.genres = genreList
                    similarMoviesList.add(similarMovie)
                    similarMoviesAdapter.notifyDataSetChanged()


                }
                for (i in 0 until GlobalData.similarSeasons2.size) {

                    val season = Saison()
                    season.title = GlobalData.similarSeasons2[i]
                    season.posterPath = GlobalData.similarSeasonsPosters2[i]
                    season.overview =  GlobalData.similarSeasonsDesc2[i]
                    season.seasonNumber = GlobalData.similarSeasonsNumber2[i]
                    similarSeasonsList.add(season)
                    similarSeasonsAdapter.notifyDataSetChanged()
                }
                crewName.setText(GlobalData.crew2[pos])
                crewBirthDate.setText(GlobalData.crewBirthDates[pos])

                descTV.setText(GlobalData.crewBio2[pos])
                Picasso.with(applicationContext).load(GlobalData.crewPhotos2[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.crewPhotos2[pos]))



            }
            3 -> {

                for (i in 0 until GlobalData.similarMovies3.size) {
                    val genreList = ArrayList<String>()
                    genreList.add(GlobalData.similarMoviesgenres3[i])
                    val similarMovie = Movie()
                    similarMovie.originalTitle = GlobalData.similarMovies3[i]
                    similarMovie.posterPath = GlobalData.similarMoviesPosters3[i]
                    similarMovie.overview = GlobalData.similarMoviesdesc3[i]
                    similarMovie.genres = genreList
                    similarMoviesList.add(similarMovie)
                    similarMoviesAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.similarSeasons3.size) {

                    val season = Saison()
                    season.title = GlobalData.similarSeasons3[i]
                    season.posterPath = GlobalData.similarSeasonsPosters3[i]
                    season.overview =  GlobalData.similarSeasonsDesc3[i]
                    season.seasonNumber = GlobalData.similarSeasonsNumber3[i]
                    similarSeasonsList.add(season)
                    similarSeasonsAdapter.notifyDataSetChanged()
                }
                crewName.setText(GlobalData.crew3[pos])
                crewBirthDate.setText(GlobalData.crewBirthDates[pos])
                descTV.setText(GlobalData.crewBio3[pos])
                Picasso.with(applicationContext).load(GlobalData.crewPhotos3[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.crewPhotos3[pos]))

            }
        }




        /*    for (i in 0 until GlobalData.similarMovies.size) {
                val genreList = ArrayList<String>()
                genreList.add(GlobalData.similarMoviesgenres[i])
                val movie = Movie()
                movie.originalTitle = GlobalData.similarMovies[i]
                movie.posterPath = GlobalData.similarMoviesPosters[i]
                movie.overview =  GlobalData.similarMoviesdesc[i]
                movie.genres = genreList
                similarMoviesList.add(movie)
                similarMoviesAdapter.notifyDataSetChanged()
            }*/




        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)

        ptMovieList.add(PTMovie("Video", GlobalData.videos[pos]))
        ptAdapter.notifyDataSetChanged()
        window.sharedElementEnterTransition = TransitionInflater.from(applicationContext).inflateTransition(R.transition.detail_activity_enter_transition)
        val handler = Handler()



        sessionTimeButton.setOnClickListener {
            val intent = Intent(this@CrewDetailsActivity, CommentsActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("Commenttype","crew")
            intent.putExtra("pos1", ptMovieList[currentPosition].type)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@CrewDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@CrewDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            }
        }

    }




}