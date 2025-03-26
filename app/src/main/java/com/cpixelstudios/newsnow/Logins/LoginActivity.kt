package com.cpixelstudios.newsnow.Logins

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cpixelstudios.newsnow.Activitys.DashBoardActivity
import com.cpixelstudios.newsnow.Api.RetrofitClient
import com.cpixelstudios.newsnow.Config.Preferences
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.Models.LoginModel
import com.cpixelstudios.newsnow.Models.LoginRequest
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(
            this,
            ContextCompat.getColor(this, R.color.colorPrimary),
            false
        )


        inits()
    }

    private fun inits() {

        binding.txtRegister.setOnClickListener {
            val animations = ViewController.animation()
            binding.txtRegister.startAnimation(animations)

            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.txtNext.setOnClickListener {
            if (!ViewController.noInterNetConnectivity(applicationContext)) {
                ViewController.customToast(applicationContext, "Please check your connection ")
            } else {
                loginApi()
            }
        }

    }

    private fun loginApi() {
        val email = binding.editEmail.text?.trim().toString()
        val password = binding.passwordEdit.text?.trim().toString()

        ViewController.hideKeyBoard(this@LoginActivity )

        if (email.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter Email")
            return
        }
        if (password.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter password")
            return
        }

        if (!ViewController.validateEmail(email)) {
            ViewController.customToast(applicationContext, "Enter Valid email")
        } else {
            ViewController.showLoading(this@LoginActivity)

            val loginRequest = LoginRequest(email = email, password = password)
            val apiServices = RetrofitClient.apiInterface
            val call = apiServices.loginApi(loginRequest)
            call.enqueue(object : Callback<LoginModel> {
                override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>) {
                    if (response.isSuccessful) {

                        if (response.body()?.status==true){
                            Preferences.saveStringValue(this@LoginActivity, Preferences.userId,response.body()?.user!!.id.toString())
                            Preferences.saveStringValue(this@LoginActivity, Preferences.name,response.body()?.user!!.name.toString())
                            Preferences.saveStringValue(this@LoginActivity, Preferences.email,response.body()?.user!!.email.toString())

                            val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            ViewController.customToast(applicationContext, "Login Failed")
                        }

                    } else {
                        ViewController.customToast(applicationContext, "Invalid Email")
                    }
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                    // Handle failure (network issues, etc.)
                    ViewController.customToast(applicationContext, "Login Failed")
                }
            })

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}