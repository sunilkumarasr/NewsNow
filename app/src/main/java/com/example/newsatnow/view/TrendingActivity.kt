package com.example.newsatnow.view

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.adapter.TrendingListAdapter
import com.example.newsatnow.databinding.ActivityTrendingBinding
import com.example.newsatnow.viewModel.TrendingViewModel

class TrendingActivity : BaseActivity() {

    var binding: ActivityTrendingBinding? = null

    lateinit var viewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.light_bg)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        binding?.recyclerTrendingList?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))

        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]
        viewModel.getTrendingDetails()!!.observe(this, Observer { serviceSetterGetter ->
            val trendingAdapter = TrendingListAdapter(serviceSetterGetter.trendingArticles)
            binding?.recyclerTrendingList?.setAdapter(trendingAdapter)
            binding?.recyclerTrendingList?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })

        binding?.imgBack?.setOnClickListener {
            finish()
        }

    }
}