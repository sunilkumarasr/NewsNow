package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.TrendingData
import com.example.newsatnow.service.Repository

class TrendingViewModel : ViewModel() {
    var servicesLiveData: MutableLiveData<TrendingData>? = null

    fun getTrendingDetails() : LiveData<TrendingData>? {
        servicesLiveData = Repository.getTrendingApiCall()
        return servicesLiveData
    }
}