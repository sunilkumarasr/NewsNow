package com.example.newsatnow.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsatnow.BaseActivity
import com.example.newsatnow.databinding.ActivityProfileBinding

class Profile : BaseActivity() {

    var binding : ActivityProfileBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflate the layout for this fragment
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

    }


}