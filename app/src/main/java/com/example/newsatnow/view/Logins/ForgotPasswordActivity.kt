package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.newsatnow.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    var binding : ActivityForgotPasswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.back?.setOnClickListener {
            finish()
        }
        binding?.buttonSubmit?.setOnClickListener {
            val intent = Intent(this, VerifyEmailOTPActivity::class.java)
            startActivity(intent)
        }

    }
}