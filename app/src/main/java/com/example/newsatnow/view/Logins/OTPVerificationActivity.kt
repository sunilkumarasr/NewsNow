package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityOtpverificationBinding

class OTPVerificationActivity : BaseActivity() {
    var binding : ActivityOtpverificationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        val signUp = "<font color=\"#f47e24\">Resend OTP</font>"
        binding?.tvResend?.text = Html.fromHtml(signUp, Html.FROM_HTML_MODE_LEGACY)
        binding?.btnConfirm?.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }
}