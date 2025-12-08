package com.example.newsatnow.view

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityManualLocationBinding

class ManualLocationActivity : BaseActivity() {
    var binding : ActivityManualLocationBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManualLocationBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.myToolbar?.title = "Search your location"
        setSupportActionBar(binding?.myToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.back_arrow)
        supportActionBar?.setHomeAsUpIndicator(upArrow)
        binding?.useLocation?.setOnClickListener {
            this.getLocation()
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}