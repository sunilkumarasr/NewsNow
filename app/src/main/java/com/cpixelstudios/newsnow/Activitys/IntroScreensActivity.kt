package com.cpixelstudios.newsnow.Activitys

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.cpixelstudios.newsnow.Adapters.IntroSliderAdapter
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.Logins.LoginActivity
import com.cpixelstudios.newsnow.Models.IntroSlide
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityIntroScreensBinding

class IntroScreensActivity : AppCompatActivity() {

    val binding: ActivityIntroScreensBinding by lazy {
        ActivityIntroScreensBinding.inflate(layoutInflater)
    }

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Top Most News",
                "The first mate and his Skipper too will do their very comfortable in their tropic island nest to till the end.",
                R.drawable.logo1
            ),
            IntroSlide(
                "Great Service",
                "The first mate and his Skipper too will do their very comfortable in their tropic island nest to till the end.",
                R.drawable.logo3
            ),
            IntroSlide(
                "Seasonal Offers",
                "The first mate and his Skipper too will do their very comfortable in their tropic island nest to till the end.",
                R.drawable.logo2
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.white),
            false
        )

        inIts()


    }


    private fun inIts() {
        binding.introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)

        binding.introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)

                // Change button text dynamically
                if (position == introSliderAdapter.itemCount - 1) {
                    binding.txtNext.text = "  Finish  "
                } else {
                    binding.txtNext.text = "  Next  "
                }
            }
        })

        binding.txtNext.setOnClickListener {
            if (binding.introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                binding.introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, LoginActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        binding.txtSkip.setOnClickListener {
            Intent(applicationContext, LoginActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }



    private fun setupIndicators() {
        binding.indicatorContainer.removeAllViews() // Clear previous indicators

        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            setMargins(8, 0, 8, 0)
        }

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext).apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
                this.layoutParams = layoutParams
            }

            binding.indicatorContainer.addView(indicators[i])
        }
    }


    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.indicatorContainer.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}