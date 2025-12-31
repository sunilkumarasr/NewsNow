package com.example.newsatnow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsatnow.adapter.TrendingOnboardingAdapter
import com.example.newsatnow.databinding.FragmentTrendingBinding
import com.example.newsatnow.viewModel.TrendingViewModel
import com.google.android.material.tabs.TabLayoutMediator

class Trending : Fragment() {
    var binding : FragmentTrendingBinding? = null
    lateinit var viewModel: TrendingViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]
        viewModel.getTrendingDetails()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->
                binding?.progressBar?.visibility = View.GONE
                binding?.introViewPager?.adapter = TrendingOnboardingAdapter(serviceSetterGetter.trendingArticles)
                binding?.intoTabLayout?.let {
                    binding?.introViewPager?.let { viewPager ->
                        TabLayoutMediator(it, viewPager) { tab, position ->

                        }
                    }
                }?.attach()
        })
        return binding?.root
    }

}