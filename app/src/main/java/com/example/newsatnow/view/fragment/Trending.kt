package com.example.newsatnow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.adapter.TrendingHomeAdapter
import com.example.newsatnow.databinding.FragmentTrendingBinding
import com.example.newsatnow.viewModel.TrendingViewModel

class Trending : Fragment() {

    var binding : FragmentTrendingBinding? = null

    lateinit var viewModel: TrendingViewModel

    lateinit var trendingAdapter: TrendingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTrendingBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[TrendingViewModel::class.java]


        binding?.recyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel.getTrendingDetails()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->

            trendingAdapter = TrendingAdapter(ArrayList(serviceSetterGetter.trendingArticles))
            binding?.recyclerView?.adapter = trendingAdapter
            binding?.recyclerView?.isNestedScrollingEnabled = false


        })


        return binding?.root
    }

}