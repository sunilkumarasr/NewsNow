package com.example.newsatnow.view.Activitys.Menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.databinding.ActivityMenuSettingsBinding

class MenuSettingsActivity : BaseActivity() {
    var binding: ActivityMenuSettingsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSettingsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true


        binding?.btnBack?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.btnBack?.startAnimation(animations)
            finish()
        }

    }
}