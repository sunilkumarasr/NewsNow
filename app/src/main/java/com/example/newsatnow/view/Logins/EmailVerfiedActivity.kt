package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityEmailVerfiedBinding
import com.example.newsatnow.view.LocationActivity

class EmailVerifiedActivity : BaseActivity() {
    var binding : ActivityEmailVerfiedBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEmailVerfiedBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.back?.setOnClickListener {
            onBackPressed()
        }
        binding?.buttonSubmit?.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
    }
}