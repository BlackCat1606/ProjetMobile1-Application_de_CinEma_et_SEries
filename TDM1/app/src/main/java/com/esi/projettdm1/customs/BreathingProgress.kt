package com.esi.projettdm1

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout

import com.esi.projettdm1.R



class BreathingProgress : FrameLayout {


    constructor(context: Context) : super(context) {
        init(context)
    }


    //XML Inflation
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    private fun init(context: Context) {

        //createMainProgressBar
        createProgress(context)

        //invalidate and redraw the views.
        invalidate()
        requestLayout()

        //nowShowBreathingAnimation
        breathingAnimation()


    }

    private fun breathingAnimation() {

        val breather = this.findViewById<FrameLayout>(R.id.breather) as FrameLayout
        animate(breather)
    }


    private fun createProgress(context: Context) {


        val r = resources
        val dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, 50f, r.displayMetrics)

        val widthPxForFixedRing = getPx(50)
        val heightPxForFixedRing = getPx(50)

        val fixedRingLayout = FrameLayout(context)
        val layoutparams = FrameLayout.LayoutParams(widthPxForFixedRing, heightPxForFixedRing, Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
        fixedRingLayout.layoutParams = layoutparams
        fixedRingLayout.setBackgroundResource(R.drawable.awesome_filmy_progress)


        val widthPxForBreathingCircle = getPx(20)
        val heightPxForBreathingCircle = getPx(20)


        val breathingCircleLayout = FrameLayout(context)
        val layoutparamsBreathing = FrameLayout.LayoutParams(widthPxForBreathingCircle, heightPxForBreathingCircle, Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL)
        breathingCircleLayout.layoutParams = layoutparamsBreathing
        breathingCircleLayout.setBackgroundResource(R.drawable.filmy_circle)
        breathingCircleLayout.id = R.id.breather


        fixedRingLayout.addView(breathingCircleLayout)
        this.addView(fixedRingLayout)

    }

    private fun getPx(dp: Int): Int {

        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager
                .defaultDisplay.getMetrics(displayMetrics)

        val logicalDensity = displayMetrics.density

        return Math.ceil((dp * logicalDensity).toDouble()).toInt()
    }


    fun animate(view: View) {

        val mAnimation = ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimation.duration = 1000
        mAnimation.repeatCount = -1
        mAnimation.repeatMode = Animation.REVERSE
        mAnimation.interpolator = AccelerateInterpolator()
        mAnimation.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {}

            override fun onAnimationRepeat(animation: Animation) {}
        })
        view.animation = mAnimation
    }

}
