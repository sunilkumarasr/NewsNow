package com.example.newsatnow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.adapter.LiveNewsAdapter
import com.example.newsatnow.adapter.PodcastsAdapter
import com.example.newsatnow.databinding.FragmentPodcastsBinding
import com.example.newsatnow.viewModel.LiveNewsViewModel
import com.example.newsatnow.viewModel.PodcastsViewModel


class Podcasts : Fragment() {
    var binding : FragmentPodcastsBinding? = null
    lateinit var viewModel: PodcastsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPodcastsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[PodcastsViewModel::class.java]
        binding?.recyclerPodcasts?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false))
        viewModel.getPodcastsDetails()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->
            val catAdapter = PodcastsAdapter(serviceSetterGetter.podcasts)
            binding?.recyclerPodcasts?.setAdapter(catAdapter)
            binding?.recyclerPodcasts?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
        })
        return binding?.root
    }

}