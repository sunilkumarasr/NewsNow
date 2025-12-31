package com.example.newsatnow.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.Preferences
import com.example.newsatnow.R
import com.example.newsatnow.adapter.NavCatAdapter
import com.example.newsatnow.databinding.ActivityMainBinding
import com.example.newsatnow.view.Activitys.Menu.ManualLocationActivity
import com.example.newsatnow.view.Activitys.Menu.MenuSettingsActivity
import com.example.newsatnow.view.Activitys.Menu.ProfileActivity
import com.example.newsatnow.view.Activitys.MenuSearchActivity
import com.example.newsatnow.view.fragment.Home
import com.example.newsatnow.view.fragment.Live
import com.example.newsatnow.view.fragment.Podcasts
import com.example.newsatnow.view.fragment.Saved
import com.example.newsatnow.view.fragment.Trending
import com.example.newsatnow.viewModel.InterestViewModel
import com.google.android.material.navigation.NavigationView


class DashBoardActivity : BaseActivity() {

    var binding : ActivityMainBinding? = null

    lateinit var userIntrestsViewModel: InterestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.light_bg)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        Preferences.saveStringValue(this@DashBoardActivity, Preferences.LOGINCHECK,
            "Open")

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
        val header = binding?.navView
        val btn_settings: ImageView? = header?.findViewById<ImageView>(R.id.btn_settings)
        val btn_search: ImageView? = header?.findViewById<ImageView>(R.id.btn_search)
        val linearProfileEdit: LinearLayout? = header?.findViewById<LinearLayout>(R.id.linearProfileEdit)
        val linearLocation: LinearLayout? = header?.findViewById<LinearLayout>(R.id.linearLocation)
        val linearLanguage: LinearLayout? = header?.findViewById<LinearLayout>(R.id.linearLanguage)
        val relativeFactCheck: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeFactCheck)
        val relativeRefer: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeRefer)
        val relativeDigitalMagazine: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeDigitalMagazine)
        val relativeVideos: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeVideos)
        val relativeViralNews: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeViralNews)
        val relativeNewsNowExclusive: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeNewsNowExclusive)
        val relativeContactUs: RelativeLayout? = header?.findViewById<RelativeLayout>(R.id.relativeContactUs)

        btn_settings?.setOnClickListener {
            startActivity(Intent(this, MenuSettingsActivity::class.java))
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        btn_search?.setOnClickListener {
            startActivity(Intent(this, MenuSearchActivity::class.java))
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        linearProfileEdit?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        linearLocation?.setOnClickListener {
            startActivity(Intent(this, ManualLocationActivity::class.java))
            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }
        linearLanguage?.setOnClickListener {

            binding?.drawerLayout?.closeDrawer(GravityCompat.END)
        }

        val navView = findViewById<NavigationView>(R.id.nav_view)
        val recyclerView = navView.findViewById<RecyclerView>(R.id.rv_categories)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.isNestedScrollingEnabled = false

        userIntrestsViewModel.getInterests()!!.observe(this) { serviceSetterGetter ->
            recyclerView.adapter = NavCatAdapter(serviceSetterGetter.all)
        }


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

