package com.esi.projettdm1.utils

import android.animation.Animator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.view.ViewGroup


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
class ElevationTransition : Transition {

    constructor() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        val elevation = transitionValues.view.elevation
        transitionValues.values.put(PROPNAME_ELEVATION, elevation)
    }

    override fun createAnimator(sceneRoot: ViewGroup, startValues: TransitionValues?, endValues: TransitionValues?): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }

        val startVal = startValues.values[PROPNAME_ELEVATION] as Float
        val endVal = endValues.values[PROPNAME_ELEVATION] as Float
        if (startVal == null || endVal == null || startVal.toFloat() == endVal.toFloat()) {
            return null
        }

        val view = endValues.view
        val a = ValueAnimator.ofFloat(startVal, endVal)
        a.addUpdateListener { animation -> view.elevation = animation.animatedValue as Float }

        return a
    }

    companion object {

        private val PROPNAME_ELEVATION = "my.elevation:transition:elevation"
    }
}