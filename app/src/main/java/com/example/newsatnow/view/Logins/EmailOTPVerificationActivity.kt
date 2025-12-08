package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityEmailOtpverificationBinding

class EmailOTPVerificationActivity : BaseActivity() {
    var binding : ActivityEmailOtpverificationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEmailOtpverificationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.back?.setOnClickListener {
            onBackPressed()
        }
        binding?.buttonSubmit?.setOnClickListener {
            val intent = Intent(this, EmailVerifiedActivity::class.java)
            startActivity(intent)
        }

    }
}