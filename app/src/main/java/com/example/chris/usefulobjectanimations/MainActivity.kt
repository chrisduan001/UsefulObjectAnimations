package com.example.chris.usefulobjectanimations

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        anticipation_anim.setOnClickListener { fragmentTransacion(AnticipationFragment()) }
        arc_anim.setOnClickListener { fragmentTransacion(ArcFragment()) }
        easeInOut_anim.setOnClickListener { fragmentTransacion(EaseInOutFragment()) }
        exaggeration_anim.setOnClickListener { fragmentTransacion(ExaggerationFragment()) }
        follow_anim.setOnClickListener { fragmentTransacion(FollowFragment()) }
        rotation_anim.setOnClickListener { fragmentTransacion(RotationFragment()) }
        squash_anim.setOnClickListener { fragmentTransacion(SquashStretchFragment()) }
        staging_anim.setOnClickListener { fragmentTransacion(StagingFragment()) }
        straightAhead_anim.setOnClickListener { fragmentTransacion(StraightAheadFragment()) }
        timing_anim.setOnClickListener { fragmentTransacion(TimingFragment()) }


        restart.setOnClickListener { supportFragmentManager.fragments.forEach { frg -> (frg as? BaseFragment)?.restartAnimation() } }
    }

    private fun fragmentTransacion(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment, null)
                .commit()
    }
}
