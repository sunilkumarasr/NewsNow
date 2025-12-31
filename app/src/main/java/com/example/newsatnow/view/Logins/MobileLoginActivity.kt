package com.example.newsatnow.view.Logins

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.example.MobileOTPRequestPostModel
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.Preferences
import com.example.newsatnow.databinding.ActivityGenarateOtpactivityBinding
import com.example.newsatnow.viewModel.GenerateOTPViewModel

class MobileLoginActivity : BaseActivity() {

    var binding : ActivityGenarateOtpactivityBinding? = null
    lateinit var viewModel: GenerateOTPViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGenarateOtpactivityBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)


        viewModel = ViewModelProvider(this)[GenerateOTPViewModel::class.java]
        val terms = "By signing in, you agree to the <u><font color=\"#f47e24\">Terms and policy</font></u>"
        val signin = "Already have an account? <font color=\"#f47e24\">Sign Up</font>"
        binding?.tvTermsPolicy?.text = Html.fromHtml(terms, Html.FROM_HTML_MODE_LEGACY)
        binding?.tvSignInLink?.text = Html.fromHtml(signin, Html.FROM_HTML_MODE_LEGACY)
        binding?.btnGetStarted?.setOnClickListener {
            if (binding?.etPhoneNumber?.text.toString().isEmpty()){
                Toast.makeText( this, "Enter Phone Number", Toast.LENGTH_SHORT).show()
            }else if (binding?.etPhoneNumber?.text.toString().length < 10){
                Toast.makeText( this, "Enter Valid Phone Number", Toast.LENGTH_SHORT).show()
            }else{
                binding?.linearLoading?.visibility = View.VISIBLE
                val loginModel = MobileOTPRequestPostModel()
                loginModel.phone = binding?.etPhoneNumber?.text.toString()
                viewModel.postMobileData(loginModel)?.observe(this,
                    Observer { serviceSetterGetter ->
                        binding?.linearLoading?.visibility = View.GONE
                        val message = serviceSetterGetter?.message
                        if (message != null) {
                            val intent = Intent(this, OTPVerificationActivity::class.java)
                            intent.putExtra("mobile",binding?.etPhoneNumber?.text.toString())
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
                        }

                    })

            }
        }

        binding?.tvSignInLink?.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

    }


}