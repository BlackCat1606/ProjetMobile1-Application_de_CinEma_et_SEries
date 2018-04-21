package com.esi.projettdm1.utils


import android.view.animation.Animation

import android.view.animation.Transformation
import android.graphics.Camera
import android.graphics.Matrix

class Rotate3dAnimation(private val fromXDegrees: Int, private val toXDegrees: Int, private val fromYDegrees: Int, private val toYDegrees: Int, private val fromZDegrees: Int, private val toZDegrees: Int) : Animation() {
    private var camera: Camera? = null
    private var width = 0
    private var height = 0

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        this.width = width / 4
        this.height = height / 4
        camera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val xDegrees = fromXDegrees + (toXDegrees - fromXDegrees) * interpolatedTime
        val yDegrees = fromYDegrees + (toYDegrees - fromYDegrees) * interpolatedTime
        val zDegrees = fromZDegrees + (toZDegrees - fromZDegrees) * interpolatedTime

        val matrix = t.matrix



        camera!!.save()
        camera!!.rotateX(xDegrees)
        camera!!.rotateY(yDegrees)
        camera!!.rotateZ(zDegrees)
        camera!!.getMatrix(matrix)
        camera!!.restore()

        matrix.preTranslate((-this.width * 2).toFloat(), (-this.height / 2).toFloat())
        matrix.postTranslate((this.width * 2).toFloat(), (this.height / 2).toFloat())

    }

}
