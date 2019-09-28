package ch.hackzurich.savethepinguins.ui

import android.graphics.Canvas

/**
 * Created by Rey on 1/12/2016.
 */
abstract class HoleDrawer(protected var mBackgroundColor: Int, protected var mHoleRadius: Int) {

    internal abstract fun draw(c: Canvas, width: Int, height: Int, x: Int, y: Int)
}