package com.example.chris.usefulobjectanimations

import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.frag_anticipation.*

/**
 * Created by Chris on 3/1/18.
 */
abstract class BaseFragment : Fragment() {

    open fun restartAnimation() {
        Log.d("aaa", "called")
    }

    fun reset(target: View) {
        target.animate().apply {
            translationX(0f)
            translationY(0f)
            rotation(0f)
            rotationX(0f)
            rotationY(0f)
            scaleX(1f)
            scaleY(1f)
            alpha(1f)
            duration = 0
        }.start()
    }
}