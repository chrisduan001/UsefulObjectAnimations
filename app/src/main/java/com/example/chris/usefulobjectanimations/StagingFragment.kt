package com.example.chris.usefulobjectanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_staging.*

/**
 * Created by Chris on 3/1/18.
 */
class StagingFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_staging, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)
        reset(target_a)
        reset(target_b)

        target.pivotX = 0f
        target.pivotY = target.height.toFloat()

        val fadeSet = AnimatorSet().apply {
            playTogether(ObjectAnimator.ofFloat(target_a, View.ALPHA, .2f), ObjectAnimator.ofFloat(target_b, View.ALPHA, .2f))
        }

        AnimatorSet().apply {
            duration = 1000
            //ease in out
            interpolator = PathInterpolator(.42f, 0f, .58f, 1f)
            playSequentially(fadeSet, ObjectAnimator.ofFloat(target, View.ROTATION, -30f))
            start()
        }
    }
}