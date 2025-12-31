package com.example.newsatnow.view.Logins

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.widget.doOnTextChanged
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityMobileOtprequestBinding
import com.google.android.gms.auth.api.identity.GetPhoneNumberHintIntentRequest
import com.google.android.gms.auth.api.identity.Identity

@Suppress("DEPRECATION")
class MobileOTPRequestActivity : BaseActivity() {

    var binding : ActivityMobileOtprequestBinding? = null
    private val REQ_PHONE_NUMBER_HINT = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMobileOtprequestBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        binding?.btnGetOtp?.setOnClickListener {
            binding?.tvResendOtp?.visibility = View.VISIBLE
            binding?.pinview?.visibility = View.VISIBLE
            binding?.enterOtp?.visibility = View.VISIBLE
            binding?.btnGetOtp?.visibility = View.GONE
        }
        binding?.etMobileNumber?.doOnTextChanged {
            text, start, before, count ->
            if (text?.length == 10){
                binding?.btnGetOtp?.visibility = View.VISIBLE
            }else{
                binding?.tvResendOtp?.visibility = View.GONE
                binding?.pinview?.visibility = View.GONE
                binding?.btnSubmitOtp?.visibility = View.GONE
                binding?.btnGetOtp?.visibility = View.GONE
                binding?.enterOtp?.visibility = View.GONE
                binding?.pinview?.text = null
            }
        }
        binding?.pinview?.doOnTextChanged { text, start, before, count ->
            if (text?.length == 6){
                binding?.btnSubmitOtp?.visibility = View.VISIBLE
            }else{
                binding?.btnSubmitOtp?.visibility = View.GONE
            }
        }
        binding?.btnSubmitOtp?.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        requestPhoneNumberHint(this)
    }
    fun requestPhoneNumberHint(activity: Activity) {
        val request = GetPhoneNumberHintIntentRequest.builder().build()
        Identity.getSignInClient(activity)
            .getPhoneNumberHintIntent(request)
            .addOnSuccessListener { pendingIntent ->
                try {
                    activity.startIntentSenderForResult(
                        pendingIntent.intentSender,
                        REQ_PHONE_NUMBER_HINT,
                        null,
                        0,
                        0,
                        0
                    )
                } catch (e: IntentSender.SendIntentException) {
                    // Handle the exception
                }
            }
            .addOnFailureListener { e ->
                // Handle the error
            }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_PHONE_NUMBER_HINT) {
            if (resultCode == RESULT_OK && data != null) {
                try {
                    val phoneNumberHintIntentResponse = Identity.getSignInClient(this)
                        .getPhoneNumberFromIntent(data)
                    val phoneNumber = phoneNumberHintIntentResponse
                    Log.d("phoneNumber",phoneNumber.replace("+91", ""))
                    binding?.etMobileNumber?.setText(phoneNumber.replace("+91", ""))
                    binding?.btnGetOtp?.visibility = View.VISIBLE

                    // Use the obtained phone number (e.g., display it, use for verification)
                } catch (e: Exception) {
                    // Handle the exception if parsing the response fails
                }
            } else {
                // User canceled the selection or an error occurred
            }
        }
    }

   }