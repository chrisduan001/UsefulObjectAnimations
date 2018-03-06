package com.example.chris.usefulobjectanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import kotlinx.android.synthetic.main.frag_rotation.*

/**
 * Created by Chris on 3/1/18.
 */
class RotationFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_rotation, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target_inter)
        reset(target_outer)

        AnimatorSet().apply {
            playTogether(buildRotatingAnimator(target_inter, 360f, 6000),
                    buildRotatingAnimator(target_outer, -360f, 3000))
            start()
        }
    }

    private fun buildRotatingAnimator(target: View, to: Float, duration: Long): ObjectAnimator {
        return ObjectAnimator.ofFloat(target, View.ROTATION, to)
                .apply {
                    setDuration(duration)
                    repeatCount = ValueAnimator.INFINITE
                    interpolator = LinearInterpolator()
                }

    }
}