package com.example.newsatnow.view.Logins

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.Preferences
import com.example.newsatnow.databinding.ActivityOtpverificationBinding
import com.example.newsatnow.view.LocationActivity

class OTPVerificationActivity : BaseActivity() {

    var binding : ActivityOtpverificationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpverificationBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)

        val mobileNumber = intent.getStringExtra("mobile")
        // Now you can use mobileNumber, e.g. show it or pass to PinView logic
        binding?.tvMobile?.text = "+91 "+mobileNumber


        binding?.pinview?.setOnClickListener {
            binding?.pinview?.requestFocus()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding?.pinview, InputMethodManager.SHOW_IMPLICIT)
        }


        val signUp = "<font color=\"#f47e24\">Resend OTP</font>"
        binding?.tvResend?.text = Html.fromHtml(signUp, Html.FROM_HTML_MODE_LEGACY)
        binding?.btnConfirm?.setOnClickListener {

            val pin = binding?.pinview?.text.toString()
            if (pin.length == 6) {
                //            Preferences.saveStringValue(this@OTPVerificationActivity, Preferences.name,
//                serviceSetterGetter.user?.name.toString())
//            Preferences.saveStringValue(this@OTPVerificationActivity, Preferences.email,
//                serviceSetterGetter.user?.email.toString())
//            Preferences.saveStringValue(this@OTPVerificationActivity, Preferences.phone,
//                serviceSetterGetter.user?.phone.toString())
//            Preferences.saveStringValue(this@OTPVerificationActivity, Preferences.is_verified,
//                serviceSetterGetter.user?.is_verified.toString())


                // proceed with verification
                val intent = Intent(this, LocationActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText( this, "Enter Valid OTP", Toast.LENGTH_SHORT).show()
            }

        }


    }

}