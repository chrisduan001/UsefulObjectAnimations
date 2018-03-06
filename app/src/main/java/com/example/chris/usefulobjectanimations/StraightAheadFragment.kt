package com.example.chris.usefulobjectanimations

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_straight_ahead.*

/**
 * Created by Chris on 3/1/18.
 */
class StraightAheadFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_straight_ahead, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target_a)
        reset(target_b)

        animate(target_a, 0)
        animate(target_b, 500)

    }

    private fun animate(view: View, delay: Long) {
        AnimatorSet().apply {
            //ease
            interpolator = PathInterpolator(0.25f, 1f, .25f, 1f)

            play(ObjectAnimator.ofFloat(view, View.SCALE_X, 2f))
                    .with(ObjectAnimator.ofFloat(view, View.SCALE_Y, 2f))
                    .after(ObjectAnimator.ofFloat(view, View.ROTATION, -45f))
                    .before(ObjectAnimator.ofFloat(view, View.SCALE_X, 1f))
                    .before(ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f))

            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    view.animate().setDuration(500).rotation(0f).start()
                }
            })

            startDelay = delay
            duration = 1000

            start()
        }
    }
}