package com.example.newsatnow.Config

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class PageRollTransformer : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.apply {
            // Make pivot along the vertical edge for folding
            pivotY = height / 2f
            pivotX = if (position < 0) width.toFloat() else 0f

            // Rotate along Y-axis to simulate fold
            rotationY = 90f * position

            // Optional: slightly scale for depth effect
            val scale = 0.85f + (1 - abs(position)) * 0.15f
            scaleX = scale
            scaleY = scale

            // Fade edges for realism
            alpha = 0.3f + (1 - abs(position)) * 0.7f
        }
    }
}