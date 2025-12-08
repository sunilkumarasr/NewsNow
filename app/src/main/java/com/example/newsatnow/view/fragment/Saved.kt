package com.example.newsatnow.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsatnow.R
import com.example.newsatnow.databinding.FragmentPodcastsBinding
import com.example.newsatnow.databinding.FragmentSavedBinding

class Saved : Fragment() {
    var binding : FragmentSavedBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        return binding?.root

    }
}