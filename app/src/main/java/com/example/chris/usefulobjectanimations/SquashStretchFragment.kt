package com.example.chris.usefulobjectanimations

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_squash_stretch.*

/**
 * Created by Chris on 3/1/18.
 */
class SquashStretchFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_squash_stretch, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(wall)
        reset(target)

        AnimatorSet().apply {
            playSequentially(buildForwardAnimator(), buildDropAnimator())
            start()
        }
    }

    private fun buildForwardAnimator() : Animator {
        target.pivotX = 0f
        target.pivotY = 0f

        val distance = wall.x - target.x
        val width = target.width

        val forwardTranslateX = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, distance - width / 2)
        val forwardScaleX = ObjectAnimator.ofFloat(target, View.SCALE_X, 1f, .5f)
        val forwardScaleY = ObjectAnimator.ofFloat(target, View.SCALE_Y, 1f, 1.5f)

        return AnimatorSet().apply {
            play(forwardTranslateX).with(forwardScaleX).with(forwardScaleY)
            duration = 600
            interpolator = PathInterpolator(1f, -1f, .95f, .89f)
        }
    }

    private fun buildDropAnimator() : Animator {
        val drop = ObjectAnimator.ofFloat(target, View.TRANSLATION_Y, 0f, wall.height / 2 + target.height / 2f)
        val alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 1f, 0f)

        return AnimatorSet().apply {
            startDelay = 300
            playTogether(drop, alpha)
            interpolator = PathInterpolator(.42f, 0f, 1f, 1f)
            duration = 600
        }
    }
}