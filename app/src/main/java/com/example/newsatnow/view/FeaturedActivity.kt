package com.example.newsatnow.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.adapter.FeaturedListAdapter
import com.example.newsatnow.databinding.ActivityFeaturedBinding
import com.example.newsatnow.viewModel.FeaturedViewModel

class FeaturedActivity : BaseActivity() {

    var binding: ActivityFeaturedBinding? = null

    lateinit var viewModel: FeaturedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturedBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.light_bg)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true
        binding?.recyclerFeaturedList?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        viewModel = ViewModelProvider(this)[FeaturedViewModel::class.java]
        viewModel.getTrendingDetails()!!.observe(this, Observer { serviceSetterGetter ->
            val trendingAdapter = FeaturedListAdapter(serviceSetterGetter.trendingArticles)
            binding?.recyclerFeaturedList?.setAdapter(trendingAdapter)
            binding?.recyclerFeaturedList?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })


        binding?.imgBack?.setOnClickListener {
            finish()
        }

    }
}