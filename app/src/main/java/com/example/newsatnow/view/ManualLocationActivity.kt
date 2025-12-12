package com.example.newsatnow.view

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.Config.ViewController
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityManualLocationBinding

class ManualLocationActivity : BaseActivity() {

    var binding : ActivityManualLocationBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.imgBack?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.imgBack?.startAnimation(animations)
            finish()
        }

        binding?.useLocation?.setOnClickListener {
            val animations = ViewController.animation()
            binding?.useLocation?.startAnimation(animations)
            this.getLocation()
        }

    }

}