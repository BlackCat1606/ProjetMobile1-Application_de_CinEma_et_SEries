package com.esi.projettdm1.utils

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewTreeObserver


class TransitionHelper {

    fun fixSharedElementTransitionForStatusAndNavigationBar(activity: Activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return

        val decor = activity.window.decorView ?: return
        activity.postponeEnterTransition()
        decor.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onPreDraw(): Boolean {
                decor.viewTreeObserver.removeOnPreDrawListener(this)
                activity.startPostponedEnterTransition()
                return true
            }
        })
    }

    fun setSharedElementEnterTransition(activity: Activity, transition: Int) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return
        activity.window.sharedElementEnterTransition = TransitionInflater.from(activity).inflateTransition(transition)
    }
}
