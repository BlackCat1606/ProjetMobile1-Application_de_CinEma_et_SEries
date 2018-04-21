package com.esi.projettdm1

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.esi.projettdm1.adapters.VideoPlayer
import com.esi.projettdm1.data.GlobalData
import com.esi.projettdm1.utils.FontChanger
import com.esi.projettdm1.utils.Rotate3dAnimation
import com.squareup.picasso.Picasso
import java.util.*

class SeriesSeatSelectionActivity : AppCompatActivity() {

    lateinit var regular: Typeface
    lateinit var bold: Typeface
    lateinit var regularFontChanger: FontChanger
    lateinit var boldFontChanger: FontChanger
    lateinit var backdropIV: ImageView
    lateinit var posterIV: ImageView
    lateinit var seriePosterCV: LinearLayout
    lateinit var videoLL: LinearLayout
    internal var pos: Int = 0
    lateinit var videoPlayer: VideoPlayer
    private val MAX_SEATS = 5


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar!!.hide()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_serie_seat_selection)
        pos = intent.getIntExtra("pos", 0)
        init()
        regularFontChanger.replaceFonts(this.findViewById<View>(android.R.id.content) as ViewGroup)
        Picasso.with(applicationContext).load(GlobalData.posters[pos]).into(backdropIV)
        Picasso.with(applicationContext).load(GlobalData.posters[pos]).into(posterIV)
        if (intent.getStringExtra("pos1") == "Picture") {
            posterIV.visibility = View.VISIBLE
            videoLL.visibility = View.GONE
        } else {
            posterIV.visibility = View.GONE
            videoLL.visibility = View.VISIBLE
        }

        window.sharedElementEnterTransition = TransitionInflater.from(applicationContext).inflateTransition(R.transition.detail_activity_enter_transition)
        val rotate3dAnimation = Rotate3dAnimation(0, 0, 0, 0, 0, 0)
        rotate3dAnimation.setDuration(300)
        rotate3dAnimation.setFillAfter(true)
        rotate3dAnimation.setFillEnabled(true)

        val handler = Handler()
        handler.postDelayed({
            seriePosterCV.startAnimation(rotate3dAnimation)
            if (intent.getStringExtra("pos1") == "Video") {
                videoPlayer.seekTo(intent.getIntExtra("currentPos", 0))
            }
            supportStartPostponedEnterTransition()
        }, 200)
    }

    fun init() {
        postponeEnterTransition()
        regular = Typeface.createFromAsset(assets, "fonts/product_san_regular.ttf")
        bold = Typeface.createFromAsset(assets, "fonts/product_sans_bold.ttf")
        regularFontChanger = FontChanger(regular)
        boldFontChanger = FontChanger(bold)
        backdropIV = findViewById(R.id.backdropIV)
        posterIV = findViewById(R.id.commentPosterIV)
        seriePosterCV = findViewById(R.id.seriePosterCard)

        videoLL = findViewById(R.id.videoLL)
        videoPlayer = VideoPlayer(this@SeriesSeatSelectionActivity)

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        videoPlayer.setLayoutParams(params)
        videoPlayer.setScaleType(VideoPlayer.ScaleType.CENTER_CROP)
        videoLL.addView(videoPlayer)
        videoPlayer.loadVideo(GlobalData.videos[pos])


    }


    fun randInt(min: Int, max: Int): Int {

        val rand = Random()


        return rand.nextInt(max - min + 1) + min

    }

    override fun onBackPressed() {
        super.onBackPressed()

        val rotate3dAnimation = Rotate3dAnimation(-20, 0, 0, 0, 0, 0)
        rotate3dAnimation.setDuration(300)
        rotate3dAnimation.setFillAfter(true)
        rotate3dAnimation.setFillEnabled(true)
        seriePosterCV.startAnimation(rotate3dAnimation)
        val handler = Handler()
        handler.postDelayed({ supportFinishAfterTransition() }, 300)
    }

    override fun onDestroy() {
        super.onDestroy()
        videoLL.removeAllViews()
    }

    companion object {
        private val MAX_SEATS = 5
    }

}