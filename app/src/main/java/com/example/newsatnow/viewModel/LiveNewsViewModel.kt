package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.LiveNewsData
import com.example.example.TrendingData
import com.example.newsatnow.service.Repository

class LiveNewsViewModel : ViewModel() {
    var servicesLiveData: MutableLiveData<LiveNewsData>? = null

    fun getLiveNewsDetails() : LiveData<LiveNewsData>? {
        servicesLiveData = Repository.getLiveNewsApiCall()
        return servicesLiveData
    }
}