package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.ArticalData
import com.example.newsatnow.service.Repository

class ArticalDetailViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<ArticalData>? = null

    fun getArticalDetail(articalId: String?) : LiveData<ArticalData>? {
        servicesLiveData = Repository.getArticalDetailsApiCall(articalId)
        return servicesLiveData
    }

}