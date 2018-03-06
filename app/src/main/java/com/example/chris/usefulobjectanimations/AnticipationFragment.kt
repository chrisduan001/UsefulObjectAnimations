package com.example.chris.usefulobjectanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationSet
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_anticipation.*

/**
 * Created by Chris on 3/1/18.
 *
 * For PathInterpolator control point
 * The value is transiting from controlX1 to controlY1, from controlX2 to controlY2
 * eg: for easeInOut controlX1 .42f to 0f: the animation is accelerating from .42f to 0f.
 * controlX2 0.58f to 1f: the animation is slow down from 0.58f to 1f
 *
 * Larger difference cause the animation to change faster, eg: controlX1 : 1, controlY1 0f will cause the animation accelerating very fast
 *
 * Refer to http://inloop.github.io/interpolator/ for more interpolator details
 */
class AnticipationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_anticipation, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)

        target.pivotX = target.width.toFloat() / 2
        target.pivotY = target.height.toFloat()

        val changeX = wall.width / 2f

        val translationX = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, 0f, -changeX)
        //view is jiggling up and down effect
        val rotation = ObjectAnimator.ofFloat(target, View.ROTATION, 0f, -20f, -10f, -30f)

        val dismissSet = AnimatorSet()
        //view drop down and disappear
        val translationXX = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, -changeX, -changeX - target.width)
        val translationY = ObjectAnimator.ofFloat(target, View.TRANSLATION_Y, 0f, target.height.toFloat())
        val alpha = ObjectAnimator.ofFloat(target, View.ALPHA, 0f)
        //rotate when dropped down
        val rotation2 = ObjectAnimator.ofFloat(target, View.ROTATION, -30f, -90f)
        dismissSet.playTogether(translationXX, translationY, alpha, rotation2)

        val set = AnimatorSet()
        set.duration = 1000
        set.playSequentially(translationX, rotation, dismissSet)
        set.interpolator = PathInterpolator(.42f, 0f, .58f, 1f)

        set.start()

    }
}