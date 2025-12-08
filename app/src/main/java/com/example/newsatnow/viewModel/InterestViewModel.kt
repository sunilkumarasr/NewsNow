package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.InterestsData
import com.example.newsatnow.service.Repository

class InterestViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<InterestsData>? = null

    fun getInterests() : LiveData<InterestsData>? {
        servicesLiveData = Repository.getInterestsApiCall()
        return servicesLiveData
    }

}