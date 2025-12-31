package com.example.newsatnow.view.Activitys

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityMenuSearchBinding

class MenuSearchActivity : BaseActivity() {

    var binding: ActivityMenuSearchBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSearchBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.app_main_color)


        binding?.btnBack?.setOnClickListener {
            finish()
        }

    }
}