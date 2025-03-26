package com.cpixelstudios.newsnow.Logins

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cpixelstudios.newsnow.Activitys.DashBoardActivity
import com.cpixelstudios.newsnow.Api.RetrofitClient
import com.cpixelstudios.newsnow.Config.Preferences
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.Logins.LoginActivity
import com.cpixelstudios.newsnow.Models.LoginModel
import com.cpixelstudios.newsnow.Models.LoginRequest
import com.cpixelstudios.newsnow.Models.RegisterModel
import com.cpixelstudios.newsnow.Models.RegisterRequest
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    val binding: ActivityRegisterBinding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        inits()
    }

    private fun inits() {

        binding.txtLogin.setOnClickListener {
            val animations = ViewController.animation()
            binding.txtLogin.startAnimation(animations)
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.from_left, R.anim.to_right)
        }

        binding.txtNext.setOnClickListener {
            val animations = ViewController.animation()
            binding.txtNext.startAnimation(animations)

            if (!ViewController.noInterNetConnectivity(applicationContext)) {
                ViewController.customToast(applicationContext, "Please check your connection ")
            } else {
                registerApi()
            }

        }
    }



    private fun registerApi() {
        val name = binding.editName.text?.trim().toString()
        val email = binding.editEmail.text?.trim().toString()
        val password = binding.editPassword.text?.trim().toString()
        val Cpassword = binding.editCPassword.text?.trim().toString()

        ViewController.hideKeyBoard(this@RegisterActivity )

        if (name.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter name")
            return
        }
        if (email.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter Email")
            return
        }
        if (password.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter password")
            return
        }
        if (Cpassword.isEmpty()) {
            ViewController.customToast(applicationContext, "Enter confirm password")
            return
        }
        if (!password.equals(Cpassword)) {
            ViewController.customToast(applicationContext, "password and confirm password not match")
            return
        }

        if (!ViewController.validateEmail(email)) {
            ViewController.customToast(applicationContext, "Enter Valid email")
        } else {
            ViewController.showLoading(this@RegisterActivity)

            val registerRequest = RegisterRequest(name = name, email = email, password = password)
            val apiServices = RetrofitClient.apiInterface
            val call = apiServices.registerApi(registerRequest)
            call.enqueue(object : Callback<RegisterModel> {
                override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                    if (response.isSuccessful) {

                        if (response.body()?.status==true){
                            ViewController.customToast(applicationContext, "Register Success")
                            Preferences.saveStringValue(this@RegisterActivity, Preferences.userId,response.body()?.user!!.id.toString())
                            Preferences.saveStringValue(this@RegisterActivity, Preferences.name,response.body()?.user!!.name.toString())
                            Preferences.saveStringValue(this@RegisterActivity, Preferences.email,response.body()?.user!!.email.toString())

                            val intent = Intent(this@RegisterActivity, DashBoardActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else {
                            ViewController.customToast(applicationContext, "Register Failed")
                        }

                    } else {
                        ViewController.customToast(applicationContext, "Register Failed")
                    }
                }

                override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                    // Handle failure (network issues, etc.)
                    ViewController.customToast(applicationContext, "Register Failed")
                }
            })

        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
    }


}