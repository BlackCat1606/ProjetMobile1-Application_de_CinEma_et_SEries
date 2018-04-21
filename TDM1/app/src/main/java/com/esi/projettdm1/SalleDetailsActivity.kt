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



class  SalleDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold:Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger:FontChanger
    lateinit var salleNameTV: TextView
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
    lateinit var locationBTN : Button




    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        salleNameTV = findViewById(R.id.salleNameTV)

        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)
        sessionLL = findViewById(R.id.sessionLL)
        timeSelectionLL = findViewById(R.id.timeSelectionLL)
        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)
        locationBTN = findViewById(R.id.locationBTN)
        ///////////////////////////////////////////////////////////////////


        ///////////////////////////////////////////////////////////////////////////////

        ///////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////

        ptMovieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptMovieList, this@SalleDetailsActivity)
        bookTicketsBTN = findViewById(R.id.bookTicketsBTN)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@SalleDetailsActivity))
        picturesRV.adapter = ptAdapter
        picturesRV.post { supportStartPostponedEnterTransition() }


        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition = middleElement

            }
        }

        ///////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////////

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



        movieTimeRV = findViewById(R.id.salleTimeRV)
        movieTimeList = ArrayList<MovieTime>()
        timeSelectionAdapter = TimeSelectionAdapter(movieTimeList, this@SalleDetailsActivity)
        val layoutManager1 = LinearLayoutManager(this@SalleDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
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
        setContentView(R.layout.activity_cinema_details)
        init()
        pos = intent.getIntExtra("pos", 0)

        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)
        salleNameTV.setText(GlobalData.salles[pos])

        descTV.setText(GlobalData.sallesMoviesDesc[pos])
        Picasso.with(applicationContext).load(GlobalData.sallesMoviesPosters[pos]).into(backdropIV)
        ptMovieList.add(PTMovie("Picture", GlobalData.sallesMoviesPosters[pos]))
        ptMovieList.add(PTMovie("Video", GlobalData.sallesMoviesVideos[pos]))
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
            val intent = Intent(this@SalleDetailsActivity, SeatSelectionActivity::class.java)
            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)

            intent.putExtra("pos", pos)
            intent.putExtra("pos1", ptMovieList[currentPosition].type)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV)  , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SalleDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV) , layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@SalleDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            }
        }

        locationBTN.setOnClickListener{

            val intent2 = Intent(this@SalleDetailsActivity, Map::class.java)
            startActivity(intent2)
            //////////////Open the MAP
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
