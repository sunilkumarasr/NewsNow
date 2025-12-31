package com.example.newsatnow.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsatnow.R
import com.example.newsatnow.adapter.CatAdapter
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.databinding.FragmentHomeBinding
import com.example.newsatnow.model.Category
import com.example.newsatnow.view.FeaturedActivity
import com.example.newsatnow.view.TrendingActivity
import com.example.newsatnow.viewModel.MainFeedViewModel

class Home : Fragment() {

   var binding : FragmentHomeBinding? = null
    lateinit var mainActivityViewModel: MainFeedViewModel

    lateinit var futuredAdapter: FuturedAdapter
    lateinit var trendingAdapter: TrendingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.recycler?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false))
        binding?.recyclerFutured?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false))
        binding?.recyclerTrending?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false))
        mainActivityViewModel = ViewModelProvider(this)[MainFeedViewModel::class.java]
        mainActivityViewModel.getFeed()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->
            val catAdapter = CatAdapter(serviceSetterGetter.categories)
            futuredAdapter = FuturedAdapter(serviceSetterGetter.featuredArticles)
            trendingAdapter = TrendingAdapter(serviceSetterGetter.trendingArticles)
            binding?.recycler?.setAdapter(catAdapter)
            binding?.recyclerFutured?.setAdapter(futuredAdapter)
            binding?.recyclerTrending?.setAdapter(trendingAdapter)
            binding?.recycler?.isNestedScrollingEnabled = false
            binding?.recyclerFutured?.isNestedScrollingEnabled = false
            binding?.recyclerTrending?.isNestedScrollingEnabled = false
            //Toast.makeText(this@MainActivity, serviceSetterGetter.featuredArticles.toString(), Toast.LENGTH_LONG).show()
            binding?.progressBar?.visibility = View.GONE
            catAdapter.setOnClickListener(object :
                CatAdapter.OnClickListener {
                override fun onClick(position: Int, model: Category) {
                    Toast.makeText(context, model.name, Toast.LENGTH_LONG).show()
                }
            })
        })
        binding?.trendingViewMore?.setOnClickListener {
            startActivity(Intent(context, TrendingActivity::class.java))
        }
        binding?.futuredViewMore?.setOnClickListener {
            startActivity(Intent(context, FeaturedActivity::class.java))
        }


        binding?.searchView?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                futuredAdapter.filter.filter(s)
                trendingAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {}
        })



        return binding?.root
    }

}