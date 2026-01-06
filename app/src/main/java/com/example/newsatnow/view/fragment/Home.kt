package com.example.newsatnow.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.example.Data
import com.example.example.FeaturedArticles
import com.example.example.TrendingArticles
import com.example.newsatnow.adapter.CatAdapter
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.TrendingHomeAdapter
import com.example.newsatnow.databinding.FragmentHomeBinding
import com.example.newsatnow.model.Category
import com.example.newsatnow.view.FeaturedActivity
import com.example.newsatnow.view.TrendingActivity
import com.example.newsatnow.viewModel.MainFeedViewModel

class Home : Fragment() {

   var binding : FragmentHomeBinding? = null
    lateinit var mainActivityViewModel: MainFeedViewModel

    lateinit var futuredAdapter: FuturedAdapter
    lateinit var trendingHomeAdapter: TrendingHomeAdapter

    var catName: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding?.recycler?.setLayoutManager(LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false))
        mainActivityViewModel = ViewModelProvider(this)[MainFeedViewModel::class.java]

        //api load
        mainActivityViewModel.getFeed()!!.observe(viewLifecycleOwner, Observer { serviceSetterGetter ->

            //Create default category All
            val defaultCategory = Category(
                id = 0,
                name = "All",
                slug = "all"
            )
            //Prepare category list
            val categoryList = mutableListOf<Category>()
            categoryList.add(defaultCategory)
            categoryList.addAll(serviceSetterGetter.categories)

            //Trends and latest
            reloadData(serviceSetterGetter)

            val catAdapter = CatAdapter(ArrayList(categoryList))
            binding?.recycler?.setAdapter(catAdapter)
            binding?.recycler?.isNestedScrollingEnabled = false
            catAdapter.setOnClickListener(object :
                CatAdapter.OnClickListener {
                override fun onClick(position: Int, model: Category) {
                   // Toast.makeText(context, model.name, Toast.LENGTH_LONG).show()

                    if (model.name.equals("All")){
                        catName = ""
                    }else{
                        catName = model.name.toString()
                    }

                    reloadData(serviceSetterGetter)
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
                trendingHomeAdapter.filter.filter(s)
            }

            override fun afterTextChanged(s: Editable?) {}
        })



        return binding?.root
    }

    private fun reloadData(serviceSetterGetter: Data) {

        val featuredList = mutableListOf<FeaturedArticles>()
        val trendingList = mutableListOf<TrendingArticles>()

        if (catName.isEmpty()) {
            // All categories
            featuredList.addAll(serviceSetterGetter.featuredArticles)
            trendingList.addAll(serviceSetterGetter.trendingArticles)
        } else {
            // Filter by category name
            featuredList.addAll(
                serviceSetterGetter.featuredArticles.filter {
                    it.category?.name.equals(catName, true)
                }
            )

            trendingList.addAll(
                serviceSetterGetter.trendingArticles.filter {
                    it.category?.name.equals(catName, true)
                }
            )
        }

        // LayoutManagers (set once is better, but keeping your structure)
        binding?.recyclerFutured?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding?.recyclerTrending?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        // ✅ USE FILTERED LISTS HERE
        futuredAdapter = FuturedAdapter(ArrayList(featuredList))
        trendingHomeAdapter = TrendingHomeAdapter(ArrayList(trendingList))

        binding?.recyclerFutured?.adapter = futuredAdapter
        binding?.recyclerTrending?.adapter = trendingHomeAdapter

        binding?.recyclerFutured?.isNestedScrollingEnabled = false
        binding?.recyclerTrending?.isNestedScrollingEnabled = false
        binding?.progressBar?.visibility = View.GONE
        binding?.txtFuturedNoDta?.visibility = View.GONE
        binding?.txtTrendNoDta?.visibility = View.GONE

        if (featuredList.isEmpty()){
            binding?.txtFuturedNoDta?.visibility = View.VISIBLE
        }

        if (trendingList.isEmpty()){
            binding?.txtTrendNoDta?.visibility = View.VISIBLE
        }


    }


}