package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.ArticalData
import com.example.example.LoginModel
import com.example.example.LoginPostData
import com.example.example.MobileOTPRequestPostModel
import com.example.example.MobileOTPRequestResponseModel
import com.example.newsatnow.service.Repository

class GenerateOTPViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<MobileOTPRequestResponseModel>? = null

    fun postMobileData(mobile  : MobileOTPRequestPostModel) : LiveData<MobileOTPRequestResponseModel>? {
        servicesLiveData = Repository.postMobileOTPRequest(mobile)
        return servicesLiveData
    }

}