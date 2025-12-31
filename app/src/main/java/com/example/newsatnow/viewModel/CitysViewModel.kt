package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.Data
import com.example.example.InterestsData
import com.example.newsatnow.model.Citys.CitysDataModel
import com.example.newsatnow.service.Repository

class CitysViewModel : ViewModel(){

    var servicesLiveData: MutableLiveData<CitysDataModel>? = null

    fun getCitys() : LiveData<CitysDataModel>? {
        servicesLiveData = Repository.getCitys()
        return servicesLiveData
    }



}