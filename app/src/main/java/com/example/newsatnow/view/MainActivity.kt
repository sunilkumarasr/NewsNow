package com.example.newsatnow.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.adapter.InterestsAdapter
import com.example.newsatnow.adapter.NavCatAdapter
import com.example.newsatnow.adapter.RecommendationsAdapter
import com.example.newsatnow.databinding.ActivityMainBinding
import com.example.newsatnow.model.Category
import com.example.newsatnow.view.fragment.Home
import com.example.newsatnow.view.fragment.Live
import com.example.newsatnow.view.fragment.Podcasts
import com.example.newsatnow.view.Profile
import com.example.newsatnow.view.fragment.Saved
import com.example.newsatnow.view.fragment.Trending
import com.example.newsatnow.viewModel.InterestViewModel
import com.example.newsatnow.viewModel.MainFeedViewModel


class MainActivity : BaseActivity() {
    lateinit var userIntrestsViewModel: InterestViewModel
    var binding : ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        userIntrestsViewModel = ViewModelProvider(this)[InterestViewModel::class.java]
        val location = getSharedPreferences("LocationPref", Context.MODE_PRIVATE)
        val userLocation = location.getString("location", "")
        binding?.location?.text = userLocation
        setCurrentFragment(Home())
        binding?.bottomNav?.setOnItemSelectedListener { menuItem ->
            // Check which menu item was clicked
            when (menuItem.itemId) {
                // If the Algorithm tab is selected, show the AlgorithmFragment
                R.id.news -> setCurrentFragment(Home())
                // If the Course tab is selected, show the CourseFragment
                R.id.trending -> setCurrentFragment(Trending())
                // If the Profile tab is selected, show the ProfileFragment
                R.id.live -> setCurrentFragment(Live())
                R.id.video -> setCurrentFragment(Podcasts())
                R.id.saved -> setCurrentFragment(Saved())
            }
            // Return true to indicate that we handled the item click
            true
        }
        binding?.navView?.setItemIconSize(getResources().getDimensionPixelSize(R.dimen.menu_icon_size))
        val header = binding?.navView?.getHeaderView(0)
        val tv_account_label: TextView? = header?.findViewById<TextView>(R.id.tv_account_label)
        val profile_edit: ImageView? = header?.findViewById<ImageView>(R.id.profile_edit)
        val btn_settings: ImageView? = header?.findViewById<ImageView>(R.id.btn_settings)
        val btn_search: ImageView? = header?.findViewById<ImageView>(R.id.btn_search)
        profile_edit?.setOnClickListener {
            val intent = android.content.Intent(this, Profile::class.java)
            startActivity(intent)
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        btn_settings?.setOnClickListener {
            val intent = android.content.Intent(this, MenuSettingsActivity::class.java)
            startActivity(intent)
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        btn_search?.setOnClickListener {
            val intent = android.content.Intent(this, MenuSearchActivity::class.java)
            startActivity(intent)
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        val recyclerView: RecyclerView? = header?.findViewById<RecyclerView>(R.id.rv_categories)
        recyclerView?.setLayoutManager(LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false))
        userIntrestsViewModel.getInterests()!!.observe(this, Observer { serviceSetterGetter ->
            val  recommendationAdapter = NavCatAdapter(serviceSetterGetter.all)
            recyclerView?.setAdapter(recommendationAdapter)
            recyclerView?.isNestedScrollingEnabled = false

        })
        binding?.menuIcon?.setOnClickListener {
            binding?.drawerLayout?.openDrawer(GravityCompat.END)
        }


    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            // Replace the fragment inside the container with the new fragment
            replace(binding?.container?.id!!, fragment)
            // Commit the transaction to actually perform the change
            commit()
        }

}

