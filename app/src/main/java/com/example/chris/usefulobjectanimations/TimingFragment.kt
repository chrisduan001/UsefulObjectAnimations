package com.example.chris.usefulobjectanimations

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_timing.*

/**
 * Created by Chris on 3/1/18.
 */
class TimingFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_timing, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target_a)
        reset(target_b)

        startRotating(target_a, PathInterpolator(.93f, 0f, .67f, 1f))
        startRotating(target_b, PathInterpolator(1f, -.97f, .23f, 1f))
    }

    private fun startRotating(view: View, interpolator: Interpolator) {
        val kf0 = Keyframe.ofFloat(0f, 0f)
        val kf1 = Keyframe.ofFloat(.4f, 90f)
        val kf2 = Keyframe.ofFloat(.6f, 90f)
        val kf3 = Keyframe.ofFloat(1f, 90f)

        val holder = PropertyValuesHolder.ofKeyframe(View.ROTATION, kf0, kf1, kf2, kf3)

        ObjectAnimator.ofPropertyValuesHolder(view, holder).apply {
            setInterpolator(interpolator)
            duration = 2000
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
            start()
        }
    }

    private fun setPivot(view: View) {
        view.pivotX = view.width.toFloat()
        view.pivotY = view.height.toFloat()
    }
}