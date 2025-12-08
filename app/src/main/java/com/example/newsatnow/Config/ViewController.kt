package com.example.newsatnow.Config

import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator

object ViewController {


    fun animation(): AnimationSet {
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            interpolator = DecelerateInterpolator()
            duration = 50
        }
        val fadeOut = AlphaAnimation(1f, 0f).apply {
            interpolator = AccelerateInterpolator()
            startOffset = 100
            duration = 100
        }
        return AnimationSet(false).apply {
            addAnimation(fadeIn)
            // Uncomment the line below if you want to add fadeOut to the animation sequence
//             addAnimation(fadeOut)
        }
    }

}