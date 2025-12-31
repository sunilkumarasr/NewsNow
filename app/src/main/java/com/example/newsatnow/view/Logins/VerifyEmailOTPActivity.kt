package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityVerifyEmailOtpactivityBinding

class VerifyEmailOTPActivity : BaseActivity() {

    var binding : ActivityVerifyEmailOtpactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVerifyEmailOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val htmlText = "Didn't you receive any code? <font color=\"#f47e24\">Resend Code.</font>"
        binding?.textResend?.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
        binding?.buttonSubmit?.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

    }
}