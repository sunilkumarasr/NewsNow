package com.example.newsatnow.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.databinding.ActivityLocationBinding
import com.example.newsatnow.view.Activitys.Menu.ManualLocationActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.schedule

class LocationActivity : BaseActivity() {

    var binding : ActivityLocationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.manualLocation?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.manualLocation?.startAnimation(animations)

            val intent = Intent(this, ManualLocationActivity::class.java)
            startActivity(intent)

        }
        binding?.locationButton?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.locationButton?.startAnimation(animations)
            this.getLocation()

        }

    }

}