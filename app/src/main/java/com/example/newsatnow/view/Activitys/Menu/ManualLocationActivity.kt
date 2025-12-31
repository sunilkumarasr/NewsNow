package com.example.newsatnow.view.Activitys.Menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.content.edit
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.R
import com.example.newsatnow.adapter.CatAdapter
import com.example.newsatnow.adapter.FuturedAdapter
import com.example.newsatnow.adapter.TrendingAdapter
import com.example.newsatnow.databinding.ActivityManualLocationBinding
import com.example.newsatnow.model.Category
import com.example.newsatnow.model.Citys.CitysList
import com.example.newsatnow.view.UserIntrestsActivity
import com.example.newsatnow.viewModel.ArticalDetailViewModel
import com.example.newsatnow.viewModel.CitysViewModel

class ManualLocationActivity : BaseActivity() {

    var binding : ActivityManualLocationBinding? = null

    lateinit var  citysViewModel: CitysViewModel
    private lateinit var cityList: List<CitysList>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.light_bg)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true


        citysViewModel = ViewModelProvider(this)[CitysViewModel::class.java]
        citysViewModel.getCitys()!!.observe(this@ManualLocationActivity, Observer { serviceSetterGetter ->
            cityList = serviceSetterGetter.data   // your API list

            val cityNames = cityList.map { it.name }

            val adapter = ArrayAdapter(
                this,
                R.layout.dropdown_item,
                cityNames
            )

            binding?.autoCity?.setAdapter(adapter)

        })
        binding?.autoCity?.setDropDownBackgroundResource(R.drawable.dropdown_bg)
        binding?.autoCity?.threshold = 2
        binding?.autoCity?.setOnItemClickListener { _, _, position, _ ->
            val selectedCityName = binding?.autoCity?.adapter?.getItem(position) as String
            val selectedCity = cityList.first { it.name == selectedCityName }
            val cityId = selectedCity.id
            Log.e("CITY_", "Selected: $selectedCityName  ID: $cityId")

            // Save locality in SharedPreferences
            val locationPref = getSharedPreferences("LocationPref", Context.MODE_PRIVATE)
            locationPref.edit {
                putString("location", selectedCityName)
                apply()
            }

            val intent = Intent(this@ManualLocationActivity, UserIntrestsActivity::class.java)
            startActivity(intent)
            finish()

        }


        binding?.imgBack?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.imgBack?.startAnimation(animations)
            finish()
        }

        binding?.useLocation?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.useLocation?.startAnimation(animations)
            this.getLocation()
        }

    }

}