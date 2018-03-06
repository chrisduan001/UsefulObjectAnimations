package com.example.chris.usefulobjectanimations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.FloatProperty
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import kotlinx.android.synthetic.main.frag_follow.*

/**
 * Created by Chris on 3/1/18.
 */
class FollowFragment : BaseFragment() {
    val SKEW_X: Property<SkewView, Float> = object : FloatProperty<SkewView>("skewx") {
        override fun setValue(p0: SkewView?, p1: Float) {
            p0?.skewX = p1
        }

        override fun get(p0: SkewView?): Float {
            return p0!!.skewX
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.frag_follow, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)
        val dur = 40L

        //SKIE_X set value will be called multiple times according to the value to set
        val skew1 = ObjectAnimator.ofFloat(target, SKEW_X, 0f).apply { duration = dur * 35 }
        //the view will rotate 20 degrees
        //Skewview canvas?.skew((skewX * Math.PI / 180f).toFloat(), 0f)
        val skew2 = ObjectAnimator.ofFloat(target, SKEW_X, 20f).apply { duration = dur * 15 }
        val skew3 = ObjectAnimator.ofFloat(target, SKEW_X, 20f).apply { duration= dur * 10 }
        val skew4 = ObjectAnimator.ofFloat(target, SKEW_X, 0f).apply { duration= dur * 30 }
        val skew5 = ObjectAnimator.ofFloat(target, SKEW_X, 0f).apply { duration= dur * 10 }

        val skewSet = AnimatorSet().apply {
            playSequentially(skew1, skew2, skew3, skew4, skew5)
            interpolator = PathInterpolator(.64f, -.36f, .1f, 1f)
        }

        val distance = target.width * 4
        val tr1 = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, -distance / 6f).apply { duration = dur * 15 }
        val tr2 = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, -distance / 6f).apply { duration = dur * 10 }
        val tr3 = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, distance.toFloat()).apply { duration = dur * 60 }
        val tr4 = ObjectAnimator.ofFloat(target, View.TRANSLATION_X, distance.toFloat()).apply { duration = dur * 15 }

        val trSet = AnimatorSet().apply {
            playSequentially(tr1, tr2, tr3, tr4)
            interpolator = PathInterpolator(.64f, -.36f, .1f, 1f)
        }

        AnimatorSet().apply {
            playTogether(skewSet, trSet)
            start()
        }

    }
}