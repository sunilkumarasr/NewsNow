package com.example.newsatnow.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.newsatnow.R
import com.example.newsatnow.databinding.ActivityWebviewBinding

class WebviewActivity : AppCompatActivity() {
    var binding : ActivityWebviewBinding? = null
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebviewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding?.root)
        val source = intent?.extras?.getString("source")
        binding?.webView?.webViewClient = WebViewClient()
        // this will load the url of the website
        binding?.webView?.loadUrl(source!!)
        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding?.webView?.settings?.javaScriptEnabled = true
        // if you want to enable zoom feature
        binding?.webView?.settings?.setSupportZoom(true)
        binding?.backArrow?.setOnClickListener {
            finish()
        }

    }
}