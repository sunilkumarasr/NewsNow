package com.example.newsatnow.view.Logins

import android.R
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.core.view.WindowCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.example.LoginPostData
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.Utils.NoSpaceInputFilter
import com.example.newsatnow.databinding.ActivityLoginBinding
import com.example.newsatnow.view.LocationActivity
import com.example.newsatnow.viewModel.LoginViewModel

class LoginActivity : BaseActivity() {
    var binding : ActivityLoginBinding? = null
    var passwordView: Boolean = false
    var loginViewModel : LoginViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val signUp = "Don\'t have an account? <font color=\"#f47e24\">Sign Up</font>"
        binding?.signup?.text = Html.fromHtml(signUp, Html.FROM_HTML_MODE_LEGACY)
        binding?.signup?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.signup?.startAnimation(animations)

            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }

        // Toggle password visibility
//        binding?.passwordToggle?.setOnClickListener {
//            binding?.etPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
//            if (passwordView){
//                passwordView = false
//                // Hide the password
//                binding?.etPassword?.transformationMethod = PasswordTransformationMethod.getInstance()
//                binding?.passwordToggle?.setImageResource(R.drawable.close_eye)
//            }else{
//                passwordView = true
//                // Show the password
//                binding?.etPassword?.transformationMethod = HideReturnsTransformationMethod.getInstance()
//                binding?.passwordToggle?.setImageResource(R.drawable.open_eye)
//            }
//
//        }

        binding?.forgotPassword?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.forgotPassword?.startAnimation(animations)

            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }


        binding?.etEmail?.filters = arrayOf(NoSpaceInputFilter())
        binding?.etPassword?.filters = arrayOf(NoSpaceInputFilter())
        binding?.loginButton?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.loginButton?.startAnimation(animations)


            if (binding?.etEmail?.text.toString().isEmpty() && binding?.etPassword?.text.toString().isEmpty()) {
                Toast.makeText(this@LoginActivity, "Enter Email and Password", Toast.LENGTH_SHORT).show()
            }else if (binding?.etEmail?.text.toString().isEmpty()) {
                Toast.makeText(this@LoginActivity, "Enter Email", Toast.LENGTH_SHORT).show()
            }else if (!Patterns.EMAIL_ADDRESS.matcher(binding?.etEmail?.text.toString()).matches()) {
                Toast.makeText(this@LoginActivity, "Invalid email address", Toast.LENGTH_SHORT).show()
            }else if (binding?.etPassword?.text.toString().isEmpty()) {
                Toast.makeText(this@LoginActivity, "Enter Password", Toast.LENGTH_SHORT).show()
            }else{
                binding?.progressBar?.visibility = View.VISIBLE
                val loginModel = LoginPostData()
                loginModel.email = binding?.etEmail?.text.toString()
                loginModel.password = binding?.etPassword?.text.toString()

                loginViewModel?.postLoginData(loginModel)!!.observe(this,
                    Observer { serviceSetterGetter ->
                        binding?.progressBar?.visibility = View.GONE
                        if (serviceSetterGetter == null) {
                            Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show()
                        } else {
                            val token = serviceSetterGetter.token
                            if (token != null) {
                                val intent = Intent(this, LocationActivity::class.java)
                                startActivity(intent)
                            }
                        }

                    })
            }

        }

    }


}