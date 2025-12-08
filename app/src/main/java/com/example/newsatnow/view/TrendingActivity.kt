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
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.adapter.TrendingListAdapter
import com.example.newsatnow.databinding.ActivityTrendingBinding
import com.example.newsatnow.viewModel.TrendingViewModel

class TrendingActivity : BaseActivity() {
    var binding: ActivityTrendingBinding? = null
    lateinit var viewModel: TrendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTrendingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding?.recyclerTrendingList?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false))
        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]
        viewModel.getTrendingDetails()!!.observe(this, Observer { serviceSetterGetter ->
            val trendingAdapter = TrendingListAdapter(serviceSetterGetter.trendingArticles)
            binding?.recyclerTrendingList?.setAdapter(trendingAdapter)
            binding?.recyclerTrendingList?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })

    }
}