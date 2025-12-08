package com.example.newsatnow.view

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.newsatnow.adapter.OnboardingAdapter
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.PageRollTransformer
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivitySlidesBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.tabs.TabLayoutMediator

class SlidesActivity : BaseActivity() {
    private lateinit var binding: ActivitySlidesBinding
    private lateinit var mFusedLocationClient: FusedLocationProviderClient
    private val permissionId = 2
    val arrayListImages  =  arrayOf(R.drawable.intro_1,R.drawable.intro_2,R.drawable.intro_3,R.drawable.intro_4)
    val arrayListTitle  =  arrayOf("Informatics News","Local News Feed","Live Stream Podcasts","News Now")
    val arrayListSubTitle  =  arrayOf("Stay informed on emerging technologies, innovations, and policy developments shaping the digital world.",
        "Get quick insights into what's happening in your city, every day.",
        "Tune into engaging podcasts for in-depth analysis, expert interviews, and daily news roundups on the go.",
        "Personalize your news experience with real-time updates, local stories, tech insights, and live broadcasts—all in one place.")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        binding = ActivitySlidesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            introViewPager.adapter = OnboardingAdapter(this,arrayListImages,arrayListTitle,arrayListSubTitle)

            // Book flip animation
            introViewPager.setPageTransformer(PageRollTransformer())

            TabLayoutMediator(intoTabLayout, introViewPager) { tab, position -> }.attach()

            // Hide TabLayout on last page
            introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position == arrayListImages.size - 1) { // last page
                        intoTabLayout.visibility = View.GONE
                    } else {
                        intoTabLayout.visibility = View.VISIBLE
                    }
                }
            })

        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

    }

}