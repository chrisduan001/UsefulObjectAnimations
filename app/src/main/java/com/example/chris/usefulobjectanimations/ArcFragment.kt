package com.example.chris.usefulobjectanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_arc.*

/**
 * Created by Chris on 3/1/18.
 */
class ArcFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_arc, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)

        val parent = target.parent as ViewGroup

        val height = (parent.height - target.height) / 16f
        val width = parent.width.toFloat()
        val duration = 4000L

        val t = duration / 100L

        val path1 = PathInterpolator(.51f, .01f, .79f, .02f) //bounce up
        val path2 = PathInterpolator(.19f, 1f, .7f, 1f) //bounce down

        val animList = listOf(
                buildSingleDropAnimator(height * -8, path1, 0), //Move view to the top position
                buildSingleDropAnimator(height * 8, path2, t * 15), //Drop down
                buildSingleDropAnimator(height * -4, path1, t * 10), //bounce up
                buildSingleDropAnimator(height * 8, path2, (t * 7.5).toLong()),
                buildSingleDropAnimator(0f, path1, (t * 7.5).toLong()),
                buildSingleDropAnimator(height * 8, path2, t * 5),
                buildSingleDropAnimator(height * 3, path1, t * 5),
                buildSingleDropAnimator(height * 8, path2, t * 6),
                buildSingleDropAnimator(height * 6, path1, t * 4),

                buildSingleDropAnimator(height * 8, path2, t * 4),
                buildSingleDropAnimator(height * 7.5f, path1, t * 2),
                buildSingleDropAnimator(height * 8, path2, t * 4)
        )

        val dropSet = AnimatorSet()
                .apply {
                    playSequentially(animList)
                    interpolator = LinearInterpolator()
                }

        val translateX = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, width)
        val alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0f)
                .apply { startDelay = duration * 7 / 10 }

        val moveSet = AnimatorSet()
                .apply {
                    interpolator = PathInterpolator(.37f, .55f, .49f, .67f)
                    setDuration(duration)
                    playTogether(translateX, alpha)
                }

        AnimatorSet()
                .apply {
                    playTogether(dropSet, moveSet)
                    start()
                }

    }

    private fun buildSingleDropAnimator(value: Float, interpolator: TimeInterpolator, duration: Long)
            = ObjectAnimator.ofFloat(target, View.TRANSLATION_Y, value)
            .apply {
                setDuration(duration)
                setInterpolator(interpolator)
            }
}