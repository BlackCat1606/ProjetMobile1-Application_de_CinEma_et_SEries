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
import kotlinx.android.synthetic.main.episode_image_item.*
import kotlinx.android.synthetic.main.fragment1_layout.view.*
import java.util.ArrayList

class EpisodeDetailsActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger
    lateinit var movieNameTV: TextView



    lateinit var descTV: TextView
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
    internal var pos: Int = 0
    lateinit var movieTimeRV: RecyclerView
    lateinit var movieTimeList: MutableList<MovieTime>
    lateinit var timeSelectionAdapter: TimeSelectionAdapter
    lateinit var detailsLL: LinearLayout
    lateinit var timeSelectionLL: LinearLayout
    internal var timeopen = false
    lateinit var bookTicketsBTN: Button
    lateinit var descCV: CardView



    fun init() {


        postponeEnterTransition()
        descCV = findViewById(R.id.descCV)
        //Changing the font throughout the activity
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        movieNameTV = findViewById(R.id.episodeNameTV)

        descTV = findViewById(R.id.descriptionTV)
        backdropIV = findViewById(R.id.backdropIV)
        sessionLL = findViewById(R.id.sessionLL)
        timeSelectionLL = findViewById(R.id.timeSelectionLL)
        detailsLL = findViewById(R.id.detailsLL)
        sessionTimeButton = findViewById(R.id.sessionTimeBTN)
        picturesRV = findViewById(R.id.picturesRV)


        ptMovieList = ArrayList<PTMovie>()
        ptAdapter = PTAdapter(ptMovieList, this@EpisodeDetailsActivity)
        bookTicketsBTN = findViewById(R.id.bookTicketsBTN)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(picturesRV)
        picturesRV.layoutManager = layoutManager
        picturesRV.addItemDecoration(LinePagerIndicatorDecoration(this@EpisodeDetailsActivity))
        picturesRV.adapter = ptAdapter
        picturesRV.post { supportStartPostponedEnterTransition() }


        val callback = object : MiddleItemFinder.MiddleItemCallback {
            override fun scrollFinished(middleElement: Int) {
                // interaction with middle item
                currentPosition = middleElement

            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////


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
        timeSelectionAdapter = TimeSelectionAdapter(movieTimeList, this@EpisodeDetailsActivity)
        val layoutManager1 = LinearLayoutManager(this@EpisodeDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
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
        setContentView(R.layout.activity_episode_details)
        init()
        pos = intent.getIntExtra("saisonPos", 0)


        val extras = intent.extras
        var episodeTitle : String  = extras.getString("episodeTitle")
        Log.d("myTITLE",episodeTitle)

        if(episodeTitle.contains("GOT"))
        {
            if(episodeTitle.contains("S1"))
            {
                movieNameTV.setText(GlobalData.GOTseason1Episodes[pos])

                descTV.setText(GlobalData.GOTseason1EpisodeDesc[pos])
                Picasso.with(applicationContext).load(GlobalData.GOTseason1EpisodesPosters[pos]).into(backdropIV)
                ptMovieList.add(PTMovie("Picture", GlobalData.GOTseason1EpisodesPosters[pos]))
                ptMovieList.add(PTMovie("Video", GlobalData.GOTseason1EpisodesVideos[pos]))
                ptAdapter.notifyDataSetChanged()

            }
            else{
                if(episodeTitle.contains("S2"))
                {
                    movieNameTV.setText(GlobalData.GOTseason2Episodes[pos])

                    descTV.setText(GlobalData.GOTseason2EpisodeDesc[pos])
                    Picasso.with(applicationContext).load(GlobalData.GOTseason2EpisodesPosters[pos]).into(backdropIV)
                    ptMovieList.add(PTMovie("Picture", GlobalData.GOTseason2EpisodesPosters[pos]))
                    ptMovieList.add(PTMovie("Video", GlobalData.GOTseason2EpisodesVideos[pos]))
                    ptAdapter.notifyDataSetChanged()


                }else{
                    if(episodeTitle.contains("S3"))
                    {

                        movieNameTV.setText(GlobalData.GOTseason3Episodes[pos])

                        descTV.setText(GlobalData.GOTseason3EpisodeDesc[pos])
                        Picasso.with(applicationContext).load(GlobalData.GOTseason3EpisodesPosters[pos]).into(backdropIV)
                        ptMovieList.add(PTMovie("Picture", GlobalData.GOTseason3EpisodesPosters[pos]))
                        ptMovieList.add(PTMovie("Video", GlobalData.GOTseason3EpisodesVideos[pos]))
                        ptAdapter.notifyDataSetChanged()

                    }else{
                        if(episodeTitle.contains("S4"))
                        {
                            movieNameTV.setText(GlobalData.GOTseason4Episodes[pos])
                            descTV.setText(GlobalData.GOTseason4EpisodeDesc[pos])
                            Picasso.with(applicationContext).load(GlobalData.GOTseason4EpisodesPosters[pos]).into(backdropIV)
                            ptMovieList.add(PTMovie("Picture", GlobalData.GOTseason4EpisodesPosters[pos]))
                            ptMovieList.add(PTMovie("Video", GlobalData.GOTseason4EpisodesVideos[pos]))
                            ptAdapter.notifyDataSetChanged()

                        }
                    }
                }
            }

        }
        else{
            if(episodeTitle.contains("DH"))
            {
                if(episodeTitle.contains("S1"))
                {
                    movieNameTV.setText(GlobalData.DHseason1Episodes[pos])

                    descTV.setText(GlobalData.DHseason1EpisodeDesc[pos])
                    Picasso.with(applicationContext).load(GlobalData.DHseason1EpisodesPosters[pos]).into(backdropIV)
                    ptMovieList.add(PTMovie("Picture", GlobalData.DHseason1EpisodesPosters[pos]))
                    ptMovieList.add(PTMovie("Video", GlobalData.DHseason1EpisodesVideos[pos]))
                    ptAdapter.notifyDataSetChanged()
                }
                else{
                    if(episodeTitle.contains("S2"))
                    {
                        movieNameTV.setText(GlobalData.DHseason2Episodes[pos])
                        descTV.setText(GlobalData.DHseason2EpisodeDesc[pos])
                        Picasso.with(applicationContext).load(GlobalData.DHseason2EpisodesPosters[pos]).into(backdropIV)
                        ptMovieList.add(PTMovie("Picture", GlobalData.DHseason2EpisodesPosters[pos]))
                        ptMovieList.add(PTMovie("Video", GlobalData.DHseason2EpisodesVideos[pos]))
                        ptAdapter.notifyDataSetChanged()

                    }else{
                        if(episodeTitle.contains("S3"))
                        {
                            movieNameTV.setText(GlobalData.DHseason3Episodes[pos])
                            descTV.setText(GlobalData.DHseason3EpisodeDesc[pos])
                            Picasso.with(applicationContext).load(GlobalData.DHseason3EpisodesPosters[pos]).into(backdropIV)
                            ptMovieList.add(PTMovie("Picture", GlobalData.DHseason3EpisodesPosters[pos]))
                            ptMovieList.add(PTMovie("Video", GlobalData.DHseason3EpisodesVideos[pos]))
                            ptAdapter.notifyDataSetChanged()


                        }else{
                            if(episodeTitle.contains("S4"))
                            {
                                movieNameTV.setText(GlobalData.DHseason4Episodes[pos])
                                descTV.setText(GlobalData.DHseason4EpisodeDesc[pos])
                                Picasso.with(applicationContext).load(GlobalData.DHseason4EpisodesPosters[pos]).into(backdropIV)
                                ptMovieList.add(PTMovie("Picture", GlobalData.DHseason4EpisodesPosters[pos]))
                                ptMovieList.add(PTMovie("Video", GlobalData.DHseason4EpisodesVideos[pos]))
                                ptAdapter.notifyDataSetChanged()

                            }
                        }
                    }
                }

            }
            else
            {
                if(episodeTitle.contains("BL"))
                {
                    if(episodeTitle.contains("S1"))
                    {
                        movieNameTV.setText(GlobalData.BLseason1Episodes[pos])
                        descTV.setText(GlobalData.BLseason1EpisodeDesc[pos])
                        Picasso.with(applicationContext).load(GlobalData.BLseason1EpisodesPosters[pos]).into(backdropIV)
                        ptMovieList.add(PTMovie("Picture", GlobalData.BLseason1EpisodesPosters[pos]))
                        ptMovieList.add(PTMovie("Video", GlobalData.BLseason1EpisodesVideos[pos]))
                        ptAdapter.notifyDataSetChanged()
                    }
                    else{
                        if(episodeTitle.contains("S2"))
                        {
                            movieNameTV.setText(GlobalData.BLseason2Episodes[pos])
                            descTV.setText(GlobalData.BLseason2EpisodeDesc[pos])
                            Picasso.with(applicationContext).load(GlobalData.BLseason2EpisodesPosters[pos]).into(backdropIV)
                            ptMovieList.add(PTMovie("Picture", GlobalData.BLseason2EpisodesPosters[pos]))
                            ptMovieList.add(PTMovie("Video", GlobalData.BLseason2EpisodesVideos[pos]))
                            ptAdapter.notifyDataSetChanged()

                        }else{
                            if(episodeTitle.contains("S3"))
                            {
                                movieNameTV.setText(GlobalData.BLseason3Episodes[pos])
                                descTV.setText(GlobalData.BLseason3EpisodeDesc[pos])
                                Picasso.with(applicationContext).load(GlobalData.BLseason3EpisodesPosters[pos]).into(backdropIV)
                                ptMovieList.add(PTMovie("Picture", GlobalData.BLseason3EpisodesPosters[pos]))
                                ptMovieList.add(PTMovie("Video", GlobalData.BLseason3EpisodesVideos[pos]))
                                ptAdapter.notifyDataSetChanged()



                            }else{
                                if(episodeTitle.contains("S4"))
                                {
                                    movieNameTV.setText(GlobalData.BLseason4Episodes[pos])
                                    descTV.setText(GlobalData.BLseason4EpisodeDesc[pos])
                                    Picasso.with(applicationContext).load(GlobalData.BLseason4EpisodesPosters[pos]).into(backdropIV)
                                    ptMovieList.add(PTMovie("Picture", GlobalData.BLseason4EpisodesPosters[pos]))
                                    ptMovieList.add(PTMovie("Video", GlobalData.BLseason4EpisodesVideos[pos]))
                                    ptAdapter.notifyDataSetChanged()


                                }
                            }
                        }
                    }

                }
                else{
                    if(episodeTitle.contains("BB"))
                    {
                        if(episodeTitle.contains("S1"))
                        {
                            movieNameTV.setText(GlobalData.BBseason1Episodes[pos])
                            descTV.setText(GlobalData.BBseason1EpisodeDesc[pos])
                            Picasso.with(applicationContext).load(GlobalData.BBseason1EpisodesPosters[pos]).into(backdropIV)
                            ptMovieList.add(PTMovie("Picture", GlobalData.BBseason1EpisodesPosters[pos]))
                            ptMovieList.add(PTMovie("Video", GlobalData.BBseason1EpisodesVideos[pos]))
                            ptAdapter.notifyDataSetChanged()
                        }
                        else{
                            if(episodeTitle.contains("S2"))
                            {
                                movieNameTV.setText(GlobalData.BBseason2Episodes[pos])
                                descTV.setText(GlobalData.BBseason2EpisodeDesc[pos])
                                Picasso.with(applicationContext).load(GlobalData.BBseason2EpisodesPosters[pos]).into(backdropIV)
                                ptMovieList.add(PTMovie("Picture", GlobalData.BBseason2EpisodesPosters[pos]))
                                ptMovieList.add(PTMovie("Video", GlobalData.BBseason2EpisodesVideos[pos]))
                                ptAdapter.notifyDataSetChanged()


                            }else{
                                if(episodeTitle.contains("S3"))
                                {
                                    movieNameTV.setText(GlobalData.BBseason3Episodes[pos])
                                    descTV.setText(GlobalData.BBseason3EpisodeDesc[pos])
                                    Picasso.with(applicationContext).load(GlobalData.BBseason3EpisodesPosters[pos]).into(backdropIV)
                                    ptMovieList.add(PTMovie("Picture", GlobalData.BBseason3EpisodesPosters[pos]))
                                    ptMovieList.add(PTMovie("Video", GlobalData.BBseason3EpisodesVideos[pos]))
                                    ptAdapter.notifyDataSetChanged()



                                }else{
                                    if(episodeTitle.contains("S4"))
                                    {
                                        movieNameTV.setText(GlobalData.BBseason4Episodes[pos])
                                        descTV.setText(GlobalData.BBseason4EpisodeDesc[pos])
                                        Picasso.with(applicationContext).load(GlobalData.BBseason4EpisodesPosters[pos]).into(backdropIV)
                                        ptMovieList.add(PTMovie("Picture", GlobalData.BBseason4EpisodesPosters[pos]))
                                        ptMovieList.add(PTMovie("Video", GlobalData.BBseason4EpisodesVideos[pos]))
                                        ptAdapter.notifyDataSetChanged()

                                    }
                                }
                            }
                        }

                    }
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
            val intent = Intent(this@EpisodeDetailsActivity, CommentsActivity::class.java)

            val transitionPairs = arrayOfNulls<Pair<View, String>>(2)



            intent.putExtra("pos", pos)
            intent.putExtra("Commenttype","episode")
            intent.putExtra("episodeTitle",episodeTitle)
            intent.putExtra("episodePoster",backdropIV.toString())
            intent.putExtra("pos1", ptMovieList[currentPosition].type)
            intent.putExtra("episodeVideo",ptMovieList[currentPosition].url)

            if (ptMovieList[currentPosition].type.equals("Picture")) {
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.pictureCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)


                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@EpisodeDetailsActivity, *transitionPairs)

                startActivity(intent, options.toBundle())
            } else {


                intent.putExtra("currentPos", ptAdapter.getCurrentProgress())
                transitionPairs[0] = Pair.create<View, String>(layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV), layoutManager.findViewByPosition(currentPosition).findViewById<View>(R.id.videoCV).getTransitionName())
                transitionPairs[1] = Pair.create(descCV as View, descCV.transitionName)

                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this@EpisodeDetailsActivity, *transitionPairs)

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