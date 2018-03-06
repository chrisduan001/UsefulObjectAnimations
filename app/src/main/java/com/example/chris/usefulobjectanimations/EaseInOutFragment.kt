package com.example.chris.usefulobjectanimations

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_arc.*

/**
 * Created by Chris on 3/1/18.
 */
class EaseInOutFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_east_in_out, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)

        val displayMetrics = Resources.getSystem().displayMetrics
        //Will need to calculate the actual x value when use in the application
        val x = displayMetrics.widthPixels * 4 / 10f

        ObjectAnimator.ofFloat(target, View.TRANSLATION_X, -x, x).apply {
            duration = 2000
            interpolator = PathInterpolator(.5f, 0f, .5f, 1f)
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }
}