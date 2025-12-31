package com.example.newsatnow.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.adapter.InterestsAdapter
import com.example.newsatnow.adapter.RecommendationsAdapter
import com.example.newsatnow.databinding.ActivityUserIntrestsBinding
import com.example.newsatnow.viewModel.InterestViewModel

class UserIntrestsActivity : BaseActivity() {

    var binding: ActivityUserIntrestsBinding? = null

    lateinit var userIntrestsViewModel: InterestViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserIntrestsBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.light_gray_bg)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        userIntrestsViewModel = ViewModelProvider(this)[InterestViewModel::class.java]
        val layoutManager = GridLayoutManager(this, 4)
        val layoutManagerRecommand = GridLayoutManager(this, 4)
        binding?.recyclerInterests?.layoutManager = layoutManager
        binding?.recyclerRecommendation?.layoutManager = layoutManagerRecommand
        userIntrestsViewModel.getInterests()!!.observe(this, Observer { serviceSetterGetter ->
            binding?.progressBar?.visibility = View.GONE
            val  intrestAdapter = InterestsAdapter(serviceSetterGetter.all)
            binding?.recyclerInterests?.setAdapter(intrestAdapter)
            binding?.recyclerInterests?.isNestedScrollingEnabled = false
            val  recommendationAdapter = RecommendationsAdapter(serviceSetterGetter.recommended)
            binding?.recyclerRecommendation?.setAdapter(recommendationAdapter)
            binding?.recyclerRecommendation?.isNestedScrollingEnabled = false

        })
        binding?.build?.setOnClickListener {
            val intent = Intent(this, DashBoardActivity::class.java)
            startActivity(intent)
        }
    }
}