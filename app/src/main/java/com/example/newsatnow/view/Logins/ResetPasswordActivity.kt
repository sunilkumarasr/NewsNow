package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : BaseActivity() {
    var binding : ActivityResetPasswordBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.buttonContinue?.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}