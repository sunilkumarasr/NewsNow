package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.FuturedData
import com.example.newsatnow.service.Repository

class FeaturedViewModel : ViewModel() {
    var servicesLiveData: MutableLiveData<FuturedData>? = null

    fun getTrendingDetails() : LiveData<FuturedData>? {
        servicesLiveData = Repository.getFeaturedApiCall()
        return servicesLiveData
    }
}