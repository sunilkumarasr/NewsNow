package com.cpixelstudios.newsnow.Activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.cpixelstudios.newsnow.Config.ViewController
import com.cpixelstudios.newsnow.R
import com.cpixelstudios.newsnow.databinding.ActivityShippingPolicyBinding

class ShippingPolicyActivity : AppCompatActivity() {

    val binding: ActivityShippingPolicyBinding by lazy {
        ActivityShippingPolicyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        ViewController.changeStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary), false)

        inIts()

    }

    private fun inIts() {
        binding.imgBack.setOnClickListener {
            val animations = ViewController.animation()
            binding.imgBack.startAnimation(animations)
            finish()
            overridePendingTransition(R.anim.from_left, R.anim.to_right)
        }

        //shippingPolicyApi()
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
    }


}