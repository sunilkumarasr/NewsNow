package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Data
import com.example.example.LiveNewsData
import com.example.newsatnow.service.Repository

class MainFeedViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<Data>? = null

    fun getFeed() : LiveData<Data>? {
        servicesLiveData = Repository.getServicesApiCall()
        return servicesLiveData
    }

}