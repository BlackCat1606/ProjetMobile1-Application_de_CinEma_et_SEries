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
import java.util.ArrayList

class SeasonDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger



    lateinit var seasonName: TextView

    lateinit var descTV: TextView
    lateinit var backdropIV: ImageView
    lateinit var sessionLL: LinearLayout
    lateinit var picturesRV: RecyclerView

    internal var seasonNB : Int = 0
    internal var seasonSerie : Int = 0


    lateinit var ptMovieList: MutableList<PTMovie>
    lateinit var ptAdapter: PTAdapter

    lateinit var layoutManager: LinearLayoutManager
    lateinit var snapHelper: SnapHelper

    lateinit var sessionTimeButton: Button
    internal var currentPosition = 0
    internal var currentPosition1 = 0
    internal var currentPosition2 = 0
    internal var currentPosition3 = 0
    internal var pos: Int = 0
    lateinit var movieTimeRV: RecyclerView
    lateinit var movieTimeList: MutableList<MovieTime>
    lateinit var timeSelectionAdapter: TimeSelectionAdapter
    lateinit var detailsLL: LinearLayout
    lateinit var timeSelectionLL: LinearLayout
    internal var timeopen = false
    lateinit var bookTicketsBTN: Button
    lateinit var descCV: CardView

    lateinit var castRV : RecyclerView
    lateinit var castAdapter : CastAdapter
    lateinit var castList : MutableList<Personne>
    lateinit var castLayoutManager : LinearLayoutManager

    lateinit var crewRV : RecyclerView
    lateinit var crewAdapter : CrewAdapter
    lateinit var crewList : MutableList<Personne>
    lateinit var crewLayoutManager : LinearLayoutManager

    lateinit var seasonEpisodesRV : RecyclerView
    lateinit var episodeAdapter : EpisodeAdapter
    lateinit var episodeList : MutableList<Episode>
    lateinit var episodeLayoutManager : LinearLayoutManager




    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        seasonName = findViewById(R.id.seasonNameTV)

        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)
        sessionLL = findViewById(R.id.sessionLL)
        timeSelectionLL = findViewById(R.id.timeSelectionLL)
        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)
        ///////////////////////////////////////////////////////////////////
        castRV = findViewById(R.id.castRV)
        castList = ArrayList<Personne>()
        castAdapter = CastAdapter(castList,this@SeasonDetailsActivity)
        castLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        castRV.layoutManager = castLayoutManager
        castRV.addItemDecoration(LinePagerIndicatorDecoration(this@SeasonDetailsActivity))
        castRV.adapter = castAdapter

        ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        crewRV = findViewById(R.id.crewRV)
        crewList = ArrayList<Personne>()
        crewAdapter = CrewAdapter(crewList,this@SeasonDetailsActivity)
        crewLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        crewRV.layoutManager = crewLayoutManager
        crewRV.addItemDecoration(LinePagerIndicatorDecoration(this@SeasonDetailsActivity))
        crewRV.adapter = crewAdapter


        ///////////////////////////////////////////////////////////////
        seasonEpisodesRV = findViewById(R.id.seasonEpisodes)
        episodeList = ArrayList<Episode>()
        episodeAdapter = EpisodeAdapter(episodeList,this@SeasonDetailsActivity)
        episodeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        seasonEpisodesRV.layoutManager = episodeLayoutManager
        seasonEpisodesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SeasonDetailsActivity))
        seasonEpisodesRV.adapter = episodeAdapter


        ///////////////////////////////////////////////////////////////////





        ////////////////////////////////////////////////////



        ///////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////

        ptMovieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptMovieList, this@SeasonDetailsActivity)
        bookTicketsBTN = findViewById(R.id.bookTicketsBTN)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SeasonDetailsActivity))
        picturesRV.adapter = ptAdapter
        picturesRV.post { supportStartPostponedEnterTransition() }


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
        val callback3 = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition3 = middleElement

            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
        castRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, castLayoutManager,
                        callback1, RecyclerView.SCROLL_STATE_IDLE))

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
                        callback2, RecyclerView.SCROLL_STATE_IDLE))

        crewRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////



        seasonEpisodesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, episodeLayoutManager,
                        callback3, RecyclerView.SCROLL_STATE_IDLE))

        seasonEpisodesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })

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



        movieTimeRV = findViewById(R.id.movieTimeRV)
        movieTimeList = ArrayList<MovieTime>()
        timeSelectionAdapter = TimeSelectionAdapter(movieTimeList, this@SeasonDetailsActivity)
        val layoutManager1 = LinearLayoutManager(this@SeasonDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
        movieTimeRV.layoutManager = layoutManager1
        movieTimeRV.adapter = timeSelectionAdapter

        movieTimeList.add(MovieTime("7:00 am", 300, 250))
        movieTimeList.add(MovieTime("11:00 am", 300, 120))
        movieTimeList.add(MovieTime("3:00 pm", 300, 60))
        movieTimeList.add(MovieTime("6:45 pm", 300, 50))
        movieTimeList.add(MovieTime("10:00 pm", 300, 170))
        timeSelectionAdapter.notifyDataSetChanged()

    }



    override fun onCreate(savedInstanceState: Bundle?) {

        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_season_details)
        init()
        pos = intent.getIntExtra("seriePos", 0)


        val extras = intent.extras
        var serieTitle : String  = extras.getString("serieNom")

        Log.d("Position", pos.toString())
        Log.d("serie",serieTitle)

        if(serieTitle.contains("Game Of Thrones"))
        {
                    when(pos)
                    {
                        0->{
                            for (i in 0 until GlobalData.GOTseason1Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.GOTseason1Episodes[i]
                                episode.posterPath = GlobalData.GOTseason1EpisodesPosters[i]
                                episode.overview =  GlobalData.GOTseason1EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.GOTseason1EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }

                        }
                        1->{
                            for (i in 0 until GlobalData.GOTseason2Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.GOTseason2Episodes[i]
                                episode.posterPath = GlobalData.GOTseason2EpisodesPosters[i]
                                episode.overview =  GlobalData.GOTseason2EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.GOTseason2EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }

                        }
                        2->{

                            for (i in 0 until GlobalData.GOTseason3Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.GOTseason3Episodes[i]
                                episode.posterPath = GlobalData.GOTseason3EpisodesPosters[i]
                                episode.overview =  GlobalData.GOTseason3EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.GOTseason3EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }
                        }
                        3-> {
                            for (i in 0 until GlobalData.GOTseason4Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.GOTseason4Episodes[i]
                                episode.posterPath = GlobalData.GOTseason4EpisodesPosters[i]
                                episode.overview =  GlobalData.GOTseason4EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.GOTseason4EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }

                        }

                    }
            seasonName.setText(GlobalData.GOTseasons[pos])

            descTV.setText(GlobalData.GOTseasonsDescs[pos])
            Picasso.with(applicationContext).load(GlobalData.GOTseasonsPosters[pos]).into(backdropIV)
            ptMovieList.add(PTMovie("Picture", GlobalData.GOTseasonsPosters[pos]))
            ptMovieList.add(PTMovie("Video", GlobalData.GOTseasonsVideos[pos]))
            ptAdapter.notifyDataSetChanged()


        }else
        {
            if(serieTitle.contains("Doctor House"))
            {
                when(pos)
                {
                    0->{
                        for (i in 0 until GlobalData.DHseason1Episodes.size)
                        {
                            val episode = Episode()
                            episode.title = GlobalData.DHseason1Episodes[i]
                            episode.posterPath = GlobalData.DHseason1EpisodesPosters[i]
                            episode.overview =  GlobalData.DHseason1EpisodeDesc[i]
                            episode.episodeNumber = GlobalData.DHseason1EpisodeNumber[i]
                            episodeList.add(episode)
                            episodeAdapter.notifyDataSetChanged()
                        }
                    }
                    1->{
                        for (i in 0 until GlobalData.DHseason2Episodes.size)
                        {
                            val episode = Episode()
                            episode.title = GlobalData.DHseason2Episodes[i]
                            episode.posterPath = GlobalData.DHseason2EpisodesPosters[i]
                            episode.overview =  GlobalData.DHseason2EpisodeDesc[i]
                            episode.episodeNumber = GlobalData.DHseason2EpisodeNumber[i]
                            episodeList.add(episode)
                            episodeAdapter.notifyDataSetChanged()
                        }

                    }
                    2->{

                        for (i in 0 until GlobalData.DHseason3Episodes.size)
                        {
                            val episode = Episode()
                            episode.title = GlobalData.DHseason3Episodes[i]
                            episode.posterPath = GlobalData.DHseason3EpisodesPosters[i]
                            episode.overview =  GlobalData.DHseason3EpisodeDesc[i]
                            episode.episodeNumber = GlobalData.DHseason3EpisodeNumber[i]
                            episodeList.add(episode)
                            episodeAdapter.notifyDataSetChanged()
                        }
                    }
                    3-> {
                        for (i in 0 until GlobalData.DHseason4Episodes.size)
                        {
                            val episode = Episode()
                            episode.title = GlobalData.DHseason4Episodes[i]
                            episode.posterPath = GlobalData.DHseason4EpisodesPosters[i]
                            episode.overview =  GlobalData.DHseason4EpisodeDesc[i]
                            episode.episodeNumber = GlobalData.DHseason4EpisodeNumber[i]
                            episodeList.add(episode)
                            episodeAdapter.notifyDataSetChanged()
                        }

                    }
                }
                seasonName.setText(GlobalData.DHseasons[pos])

                descTV.setText(GlobalData.DHseasonsDescs[pos])
                Picasso.with(applicationContext).load(GlobalData.DHseasonsPosters[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.DHseasonsPosters[pos]))
                ptMovieList.add(PTMovie("Video", GlobalData.DHseasonsVideos[pos]))
                ptAdapter.notifyDataSetChanged()


            }
            else{
                if(serieTitle.contains("BlackList"))
                {
                    when(pos)
                    {
                        0->{
                            for (i in 0 until GlobalData.BLseason1Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.BLseason1Episodes[i]
                                episode.posterPath = GlobalData.BLseason1EpisodesPosters[i]
                                episode.overview =  GlobalData.BLseason1EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.BLseason1EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }
                        }
                        1->{
                            for (i in 0 until GlobalData.BLseason2Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.BLseason2Episodes[i]
                                episode.posterPath = GlobalData.BLseason2EpisodesPosters[i]
                                episode.overview =  GlobalData.BLseason2EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.BLseason2EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }

                        }
                        2->{

                            for (i in 0 until GlobalData.BLseason3Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.BLseason3Episodes[i]
                                episode.posterPath = GlobalData.BLseason3EpisodesPosters[i]
                                episode.overview =  GlobalData.BLseason3EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.BLseason3EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }
                        }
                        3-> {
                            for (i in 0 until GlobalData.BLseason4Episodes.size)
                            {
                                val episode = Episode()
                                episode.title = GlobalData.BLseason4Episodes[i]
                                episode.posterPath = GlobalData.BLseason4EpisodesPosters[i]
                                episode.overview =  GlobalData.BLseason4EpisodeDesc[i]
                                episode.episodeNumber = GlobalData.BLseason4EpisodeNumber[i]
                                episodeList.add(episode)
                                episodeAdapter.notifyDataSetChanged()
                            }

                        }
                    }
                    seasonName.setText(GlobalData.BLseasons[pos])

                    descTV.setText(GlobalData.BLseasonsDescs[pos])
                    Picasso.with(applicationContext).load(GlobalData.BLseasonsPosters[pos]).into(backdropIV)
                    ptMovieList.add(PTMovie("Picture", GlobalData.BLseasonsPosters[pos]))
                    ptMovieList.add(PTMovie("Video", GlobalData.BLseasonsVideos[pos]))
                    ptAdapter.notifyDataSetChanged()



                }
                else{
                    if(serieTitle.contains("Breaking BAD"))
                    {

                        when(pos)
                        {
                            0->{
                                for (i in 0 until GlobalData.BBseason1Episodes.size)
                                {
                                    val episode = Episode()
                                    episode.title = GlobalData.BBseason1Episodes[i]
                                    episode.posterPath = GlobalData.BBseason1EpisodesPosters[i]
                                    episode.overview =  GlobalData.BBseason1EpisodeDesc[i]
                                    episode.episodeNumber = GlobalData.BBseason1EpisodeNumber[i]
                                    episodeList.add(episode)
                                    episodeAdapter.notifyDataSetChanged()
                                }
                            }
                            1->{
                                for (i in 0 until GlobalData.BBseason2Episodes.size)
                                {
                                    val episode = Episode()
                                    episode.title = GlobalData.BBseason2Episodes[i]
                                    episode.posterPath = GlobalData.BBseason2EpisodesPosters[i]
                                    episode.overview =  GlobalData.BBseason2EpisodeDesc[i]
                                    episode.episodeNumber = GlobalData.BBseason2EpisodeNumber[i]
                                    episodeList.add(episode)
                                    episodeAdapter.notifyDataSetChanged()
                                }

                            }
                            2->{

                                for (i in 0 until GlobalData.BBseason3Episodes.size)
                                {
                                    val episode = Episode()
                                    episode.title = GlobalData.BBseason3Episodes[i]
                                    episode.posterPath = GlobalData.BBseason3EpisodesPosters[i]
                                    episode.overview =  GlobalData.BBseason3EpisodeDesc[i]
                                    episode.episodeNumber = GlobalData.BBseason3EpisodeNumber[i]
                                    episodeList.add(episode)
                                    episodeAdapter.notifyDataSetChanged()
                                }
                            }
                            3-> {
                                for (i in 0 until GlobalData.BBseason4Episodes.size)
                                {
                                    val episode = Episode()
                                    episode.title = GlobalData.BBseason4Episodes[i]
                                    episode.posterPath = GlobalData.BBseason4EpisodesPosters[i]
                                    episode.overview =  GlobalData.BBseason4EpisodeDesc[i]
                                    episode.episodeNumber = GlobalData.BBseason4EpisodeNumber[i]
                                    episodeList.add(episode)
                                    episodeAdapter.notifyDataSetChanged()
                                }

                            }
                        }
                        seasonName.setText(GlobalData.BBseasons[pos])

                        descTV.setText(GlobalData.BBseasonsDescs[pos])
                        Picasso.with(applicationContext).load(GlobalData.BBseasonsPosters[pos]).into(backdropIV)
                        ptMovieList.add(PTMovie("Picture", GlobalData.BBseasonsPosters[pos]))
                        ptMovieList.add(PTMovie("Video", GlobalData.BBseasonsVideos[pos]))
                        ptAdapter.notifyDataSetChanged()



                    }
                }
            }

        }
        when (pos) {
            0 -> {
                for (i in 0 until GlobalData.castSerie0.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.castSerie0[i]
                    cast.posterPath = GlobalData.castSeriePhotos0[i]
                    cast.biographie =  GlobalData.castSerieBio0[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crewSerie0.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crewSerie0[i]
                    crew.posterPath = GlobalData.crewSeriePhotos0[i]
                    crew.biographie =  GlobalData.crewSerieBio0[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            1 -> {

                for (i in 0 until GlobalData.castSerie1.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.castSerie1[i]
                    cast.posterPath = GlobalData.castSeriePhotos1[i]
                    cast.biographie =  GlobalData.castSerieBio1[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crewSerie1.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crewSerie1[i]
                    crew.posterPath = GlobalData.crewSeriePhotos1[i]
                    crew.biographie =  GlobalData.crewSerieBio1[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            2 -> {

                for (i in 0 until GlobalData.castSerie2.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.castSerie2[i]
                    cast.posterPath = GlobalData.castSeriePhotos2[i]
                    cast.biographie =  GlobalData.castSerieBio2[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crewSerie2.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crewSerie2[i]
                    crew.posterPath = GlobalData.crewSeriePhotos2[i]
                    crew.biographie =  GlobalData.crewSerieBio2[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }

            }
            3 -> {


                for (i in 0 until GlobalData.castSerie3.size) {


                    val cast = Personne()
                    cast.nom = GlobalData.castSerie3[i]
                    cast.posterPath = GlobalData.castSeriePhotos3[i]
                    cast.biographie =  GlobalData.castSerieBio3[i]

                    castList.add(cast)
                    castAdapter.notifyDataSetChanged()
                }
                for (i in 0 until GlobalData.crewSerie3.size) {


                    val crew = Personne()
                    crew.nom = GlobalData.crewSerie3[i]
                    crew.posterPath = GlobalData.crewSeriePhotos3[i]
                    crew.biographie =  GlobalData.crewSerieBio3[i]

                    crewList.add(crew)
                    crewAdapter.notifyDataSetChanged()
                }


            }
        }





        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)


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
            val intent = Intent(this@SeasonDetailsActivity, CommentsActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("Commenttype","saison")
            intent.putExtra("seasonTitle",serieTitle)
            intent.putExtra("seasonPoster",backdropIV.toString())
            intent.putExtra("pos1", ptMovieList[currentPosition].type)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SeasonDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SeasonDetailsActivity, *transitionPairs)

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