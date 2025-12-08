package com.example.newsatnow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.R
import com.example.newsatnow.adapter.CatAdapter
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.LiveNewsAdapter
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.databinding.FragmentHomeBinding
import com.example.newsatnow.databinding.FragmentLiveBinding
import com.example.newsatnow.viewModel.LiveNewsViewModel


class Live : Fragment() {

    var binding : FragmentLiveBinding? = null
    lateinit var viewModel: LiveNewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLiveBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LiveNewsViewModel::class.java]
        binding?.recyclerLiveNews?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false))
        viewModel.getLiveNewsDetails()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->
            val catAdapter = LiveNewsAdapter(serviceSetterGetter.liveNews)
            binding?.recyclerLiveNews?.setAdapter(catAdapter)
            binding?.recyclerLiveNews?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })

        return binding?.root
    }

}