package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.LiveNewsData
import com.example.example.PodcastsData
import com.example.example.TrendingData
import com.example.newsatnow.service.Repository

class PodcastsViewModel : ViewModel() {
    var servicesLiveData: MutableLiveData<PodcastsData>? = null

    fun getPodcastsDetails() : LiveData<PodcastsData>? {
        servicesLiveData = Repository.getPodcastsApiCall()
        return servicesLiveData
    }
}