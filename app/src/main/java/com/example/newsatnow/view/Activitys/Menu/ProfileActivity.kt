package com.example.newsatnow.view.Activitys.Menu

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.databinding.ActivityProfileBinding
import com.example.newsatnow.view.DashBoardActivity

class ProfileActivity : BaseActivity() {

    var binding : ActivityProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        window.statusBarColor = ContextCompat.getColor(this, com.example.newsatnow.R.color.app_main_color)

        inits()

    }

    private fun inits() {

        binding?.btnBack?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.btnBack?.startAnimation(animations)
            finish()
        }
        binding?.linearProfile?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.linearProfile?.startAnimation(animations)
            startActivity(Intent(this, EditProfileActivity::class.java))
        }
        binding?.buttonHome?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.buttonHome?.startAnimation(animations)
            startActivity(Intent(this, DashBoardActivity::class.java))
        }


    }


}