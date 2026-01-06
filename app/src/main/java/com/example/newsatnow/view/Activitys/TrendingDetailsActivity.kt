package com.example.newsatnow.view.Activitys

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.newsatnow.adapter.TrendingOnboardingAdapter
import com.example.newsatnow.databinding.ActivityTrandingDatailsBinding
import com.example.newsatnow.viewModel.TrendingViewModel
import com.google.android.material.tabs.TabLayoutMediator

class TrendingDetailsActivity : AppCompatActivity() {

    var binding: ActivityTrandingDatailsBinding? = null

    lateinit var viewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTrandingDatailsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val selectedId = intent.extras?.getString("id")

        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]

        viewModel.getTrendingDetails()!!.observe(this) { serviceSetterGetter ->
            binding?.progressBar?.visibility = View.GONE

            val list = serviceSetterGetter.trendingArticles
            val adapter = TrendingOnboardingAdapter(list)

            binding?.introViewPager?.adapter = adapter

            // selected index id matches
            val startPosition = list.indexOfFirst {
                it.id?.toString() == selectedId
            }

            // Set ViewPager to matched item
            if (startPosition != -1) {
                binding?.introViewPager?.setCurrentItem(startPosition, false)
            }

            //animation
//            binding?.introViewPager?.setPageTransformer { page, position ->
//                page.cameraDistance = 20000f // gives depth for 3D rotation
//
//                if (position < -1) { // page is off-screen left
//                    page.alpha = 0f
//                } else if (position <= 1) { // [-1,1] visible page
//                    page.alpha = 1f
//                    page.pivotX = if (position < 0) page.width.toFloat() else 0f
//                    page.pivotY = page.height / 2f
//                    page.rotationY = -90f * position // rotate along Y axis
//                } else { // page off-screen right
//                    page.alpha = 0f
//                }
//            }

            TabLayoutMediator(
                binding!!.intoTabLayout,
                binding!!.introViewPager
            ) { _, _ -> }.attach()

        }


        binding!!.backArrow.setOnClickListener {
            finish()
        }


    }
}