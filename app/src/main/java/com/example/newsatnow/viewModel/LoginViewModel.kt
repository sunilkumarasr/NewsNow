package com.example.newsatnow.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.example.InterestsData
import com.example.example.LoginModel
import com.example.example.LoginPostData
import com.example.newsatnow.service.Repository

class LoginViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<LoginModel>? = null

    fun postLoginData(loginPostData  : LoginPostData) : LiveData<LoginModel>? {
        servicesLiveData = Repository.postLoginData(loginPostData)
        return servicesLiveData
    }

}