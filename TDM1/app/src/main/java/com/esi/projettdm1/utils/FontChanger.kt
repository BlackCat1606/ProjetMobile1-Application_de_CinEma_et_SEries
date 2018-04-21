package com.esi.projettdm1.utils

import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FontChanger(private val typeface: Typeface) {

    fun replaceFonts(viewTree: ViewGroup) {
        var child: View
        for (i in 0 until viewTree.childCount) {
            child = viewTree.getChildAt(i)
            if (child is ViewGroup) {
                replaceFonts(child)
            } else if (child is TextView) {
                child.typeface = typeface
            }
        }
    }
}


