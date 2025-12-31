package com.example.newsatnow.view.Activitys.Menu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityMenuSearchBinding
import com.example.newsatnow.databinding.ActivityMyProfileBinding

class EditProfileActivity : AppCompatActivity() {

    var binding: ActivityMyProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.white)
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        binding?.btnBack?.setOnClickListener {
            finish()
        }


    }
}