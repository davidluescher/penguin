package ch.hackzurich.savethepinguins.ui

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RadialGradient
import android.graphics.Shader

/**
 * Created by Rey on 1/12/2016.
 */
class GradientHoleDrawer(backgroundColor: Int, holeRadius: Int) :
    HoleDrawer(backgroundColor, holeRadius) {
    private var mShader: RadialGradient? = null
    private val mPaint: Paint

    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mX: Int = 0
    private var mY: Int = 0

    init {

        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.style = Paint.Style.FILL
    }

    private fun prepareShader(width: Int, height: Int, x: Int, y: Int) {
        if (mWidth != width || mHeight != height || mX != x || mY != y) {
            mWidth = width
            mHeight = height
            mX = x
            mY = y

            mShader = RadialGradient(
                mX.toFloat(),
                mY.toFloat(),
                mHoleRadius.toFloat(),
                intArrayOf(Color.TRANSPARENT, Color.TRANSPARENT, mBackgroundColor),
                GRADIENT_STOPS,
                Shader.TileMode.CLAMP
            )
            mPaint.shader = mShader
        }
    }

    override fun draw(c: Canvas, width: Int, height: Int, x: Int, y: Int) {
        prepareShader(width, height, x, y)
        c.drawRect(0f, 0f, width.toFloat(), height.toFloat(), mPaint)
    }

    companion object {

        private val GRADIENT_STOPS = floatArrayOf(0f, 0.99f, 1f)
    }

}