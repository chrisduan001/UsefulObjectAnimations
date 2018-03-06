package com.example.chris.usefulobjectanimations

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Property
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.PathInterpolator
import android.widget.ViewAnimator
import kotlinx.android.synthetic.main.frag_exaggeration.*

/**
 * Created by Chris on 3/1/18.
 */
class ExaggerationFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.frag_exaggeration, container, false)

        return view
    }

    override fun restartAnimation() {
        reset(target)

        target.pivotX = target.width.toFloat()
        target.pivotY = target.height.toFloat()

        ObjectAnimator.ofPropertyValuesHolder(
                target,
                buildRotationHolder(),
                buildScaleHolder(View.SCALE_X),
                buildScaleHolder(View.SCALE_Y))
                .apply {
                    duration = 2000
                    repeatCount = ValueAnimator.INFINITE
                    repeatMode = ValueAnimator.REVERSE
                    start()
                }
    }

    private fun buildRotationHolder() : PropertyValuesHolder {
        //Keyframe holds time and value pair
        //kf0, kf1 makes sure the view starts at the 0, 0 position
        //kf2 at 0.4f rotate the view -45 degrees
        //kf3 at .7f rotate the view 360 degrees
        val kf0 = Keyframe.ofFloat(0f, 0f)
        val kf1 = Keyframe.ofFloat(0.1f, 0f)
        kf1.interpolator = PathInterpolator(.87f, -1f, .66f, 1f)
        val kf2 = Keyframe.ofFloat(0.4f, -45f)
        kf2.interpolator = PathInterpolator(.16f, .54f, 0f, 1f)
        val kf3 = Keyframe.ofFloat(.7f, 360f)

        return PropertyValuesHolder.ofKeyframe(View.ROTATION, kf0, kf1, kf2, kf3)
    }

    private fun buildScaleHolder(property: Property<View, Float>) : PropertyValuesHolder {
        //PathInterpolator .87f to -1f will cause the view to shrink
        val kf0 = Keyframe.ofFloat(0f, 1f)
        val kf1 = Keyframe.ofFloat(.4f, 2f)
        kf1.interpolator = PathInterpolator(.87f, -1f, .66f, 1f)

        val kf2 = Keyframe.ofFloat(.7f, 1f)
        kf2.interpolator = PathInterpolator(.16f, .54f, 0f, 1f)

        val kf3 = Keyframe.ofFloat(1f, 1f)

        return PropertyValuesHolder.ofKeyframe(property, kf0, kf1, kf2, kf3)
    }
}