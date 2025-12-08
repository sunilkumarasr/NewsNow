package com.example.newsatnow.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.adapter.CatAdapter
import com.example.newsatnow.adapter.FeaturedListAdapter
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.adapter.TrendingListAdapter
import com.example.newsatnow.databinding.ActivityFeaturedBinding
import com.example.newsatnow.databinding.ActivityTrendingBinding
import com.example.newsatnow.viewModel.FeaturedViewModel
import com.example.newsatnow.viewModel.TrendingViewModel

class FeaturedActivity : BaseActivity() {
    var binding: ActivityFeaturedBinding? = null
    lateinit var viewModel: FeaturedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFeaturedBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding?.recyclerFeaturedList?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        viewModel = ViewModelProvider(this)[FeaturedViewModel::class.java]
        viewModel.getTrendingDetails()!!.observe(this, Observer { serviceSetterGetter ->
            val trendingAdapter = FeaturedListAdapter(serviceSetterGetter.trendingArticles)
            binding?.recyclerFeaturedList?.setAdapter(trendingAdapter)
            binding?.recyclerFeaturedList?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })

    }
}