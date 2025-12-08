package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityEmailVerificationBinding

class EmailVerificationActivity : BaseActivity() {
    var binding : ActivityEmailVerificationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEmailVerificationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.buttonSubmit?.setOnClickListener {
            val intent = Intent(this, EmailOTPVerificationActivity::class.java)
            startActivity(intent)
        }
        binding?.back?.setOnClickListener {
            onBackPressed()
        }
    }
}