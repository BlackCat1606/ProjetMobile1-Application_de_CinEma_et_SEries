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
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import com.esi.projettdm1.utils.FontChanger
import com.esi.projettdm1.utils.LinePagerIndicatorDecoration
import com.esi.projettdm1.utils.MiddleItemFinder
import com.esi.projettdm1.utils.Rotate3dAnimation
import com.esi.projettdm1.utils.TransitionHelper
import com.squareup.picasso.Picasso
import com.esi.projettdm1.R
import com.esi.projettdm1.adapters.*
import com.esi.projettdm1.data.*
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.cast_column_item.view.*
import kotlinx.android.synthetic.main.picture_column_item.*



class  MovieDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold:Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger:FontChanger
    lateinit var movieNameTV: TextView
    lateinit var genreTV:TextView
    lateinit var descTV:TextView
    lateinit var backdropIV: ImageView
    lateinit var sessionLL: LinearLayout
    lateinit var picturesRV: RecyclerView
    lateinit var ptMovieList: MutableList<PTMovie>
    lateinit var ptAdapter: PTAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var snapHelper: SnapHelper
    lateinit var similarMoviesSnapHelper : SnapHelper
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
    lateinit var timeSelectionLL:LinearLayout
    internal var timeopen = false
    lateinit var bookTicketsBTN: Button
    lateinit var descCV: CardView
    lateinit var commentsBTN : Button
    lateinit var similarMoviesRV : RecyclerView
    lateinit var similarMoviesAdapter : SimilarMoviesAdapter
    lateinit var similarMoviesList : MutableList<Movie>
    lateinit var similarMoviesLayoutManager : LinearLayoutManager

    lateinit var castRV : RecyclerView
    lateinit var castAdapter : CastAdapter
    lateinit var castList : MutableList<Personne>
    lateinit var castLayoutManager : LinearLayoutManager

    lateinit var crewRV : RecyclerView
    lateinit var crewAdapter : CrewAdapter
    lateinit var crewList : MutableList<Personne>
    lateinit var crewLayoutManager : LinearLayoutManager

    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        movieNameTV = findViewById(R.id.movieNameTV)
        genreTV = findViewById(R.id.genreTV)
        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)
        sessionLL = findViewById(R.id.sessionLL)
        timeSelectionLL = findViewById(R.id.timeSelectionLL)
        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)
        ///////////////////////////////////////////////////////////////////
        similarMoviesRV = findViewById(R.id.similarMoviesRV)
        similarMoviesList = ArrayList<Movie>()
        similarMoviesAdapter = SimilarMoviesAdapter(similarMoviesList,this@MovieDetailsActivity)
        similarMoviesLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
       //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        similarMoviesRV.layoutManager = similarMoviesLayoutManager
        similarMoviesRV.addItemDecoration(LinePagerIndicatorDecoration(this@MovieDetailsActivity))
        similarMoviesRV.adapter = similarMoviesAdapter

        ///////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////
        castRV = findViewById(R.id.castRV)
        castList = ArrayList<Personne>()
        castAdapter = CastAdapter(castList,this@MovieDetailsActivity)
        castLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        castRV.layoutManager = castLayoutManager
        castRV.addItemDecoration(LinePagerIndicatorDecoration(this@MovieDetailsActivity))
        castRV.adapter = castAdapter

        ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////
        crewRV = findViewById(R.id.crewRV)
        crewList = ArrayList<Personne>()
        crewAdapter = CrewAdapter(crewList,this@MovieDetailsActivity)
        crewLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        //  similarMoviesSnapHelper = PagerSnapHelper()
        // similarMoviesSnapHelper.attachToRecyclerView(similarMoviesRV)

        crewRV.layoutManager = crewLayoutManager
        crewRV.addItemDecoration(LinePagerIndicatorDecoration(this@MovieDetailsActivity))
        crewRV.adapter = crewAdapter

        ////////////////////////////////////////////////////

        ptMovieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptMovieList, this@MovieDetailsActivity)
        bookTicketsBTN = findViewById(R.id.bookTicketsBTN)
        commentsBTN = findViewById(R.id.commentsBTN)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@MovieDetailsActivity))
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
     similarMoviesRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, similarMoviesLayoutManager,
                        callback1, RecyclerView.SCROLL_STATE_IDLE))

      similarMoviesRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                println("Testing " + dx)

            }
        })
        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////
       castRV.addOnScrollListener(
                MiddleItemFinder(applicationContext, castLayoutManager,
                        callback2, RecyclerView.SCROLL_STATE_IDLE))

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
                        callback3, RecyclerView.SCROLL_STATE_IDLE))

        crewRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
        timeSelectionAdapter = TimeSelectionAdapter(movieTimeList, this@MovieDetailsActivity)
        val layoutManager1 = LinearLayoutManager(this@MovieDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
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
        setContentView(R.layout.activity_movie_details)
        init()
        pos = intent.getIntExtra("pos", 0)
        Log.d("Position",pos.toString())

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



        pos = intent.getIntExtra("pos", 0)
        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)
        movieNameTV.setText(GlobalData.movies[pos])
        genreTV.setText(GlobalData.genres[pos])
        descTV.setText(GlobalData.desc[pos])
        Picasso.with(applicationContext).load(GlobalData.posters[pos]).into(backdropIV)
        ptMovieList.add(PTMovie("Picture", GlobalData.posters[pos]))
        ptMovieList.add(PTMovie("Video", GlobalData.videos[pos]))
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
            val intent = Intent(this@MovieDetailsActivity, SeatSelectionActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("pos1", ptMovieList[currentPosition].type)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV)  , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MovieDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV) , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MovieDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            }
        }
        commentsBTN.setOnClickListener {
            val intent = Intent(this@MovieDetailsActivity, CommentsActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("Commenttype","movie")
            intent.putExtra("pos1", ptMovieList[currentPosition].type)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV)  , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MovieDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV) , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@MovieDetailsActivity, *transitionPairs)

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
