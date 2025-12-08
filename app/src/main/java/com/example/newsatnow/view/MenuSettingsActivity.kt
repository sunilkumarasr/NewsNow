package com.example.newsatnow.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityMenuSettingsBinding

class MenuSettingsActivity : BaseActivity() {
    var binding: ActivityMenuSettingsBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuSettingsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        enableEdgeToEdge()
        binding?.btnBack?.setOnClickListener {
            finish()
        }

    }
}