package com.example.chris.usefulobjectanimations

import android.content.Context
import android.graphics.Canvas
import android.support.annotation.StyleRes
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet

/**
 * Created by Chris on 3/5/18.
 */
class SkewView : AppCompatImageView {

    var skewX: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?): super(context,  attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        if (skewX != 0f) {
            canvas?.skew((skewX * Math.PI / 180f).toFloat(), 0f)
        }
        super.onDraw(canvas)
    }
}