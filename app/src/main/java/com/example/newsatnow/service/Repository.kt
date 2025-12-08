package com.example.newsatnow.service

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.example.ArticalData
import com.example.example.ArticalDataModel
import com.example.example.Data
import com.example.example.FuturedData
import com.example.example.InterestsData
import com.example.example.InterestsModel
import com.example.example.LoginModel
import com.example.example.LoginPostData
import com.example.example.NewsScreenModel
import com.example.example.TrendingData
import com.example.example.FuturedDataModel
import com.example.example.LiveNewsData
import com.example.example.LiveNewsModel
import com.example.example.MobileOTPRequestPostModel
import com.example.example.MobileOTPRequestResponseModel
import com.example.example.PodcastsData
import com.example.example.PodcastsDataModel
import com.example.example.TrendingDataModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    val serviceSetterGetter = MutableLiveData<Data?>()
    val serviceSetterGetterLiveNews = MutableLiveData<LiveNewsData?>()
    val serviceSetterGetterPodcasts = MutableLiveData<PodcastsData?>()
    val serviceTrendingSetterGetter = MutableLiveData<TrendingData?>()
    val serviceFeaturedSetterGetter = MutableLiveData<FuturedData?>()
    val serviceSetterGetterArticalDetail = MutableLiveData<ArticalData?>()
    val serviceSetterGetterInterests = MutableLiveData<InterestsData?>()
    val loginData = MutableLiveData<LoginModel?>()
    val mobileOtpReq = MutableLiveData<MobileOTPRequestResponseModel?>()

    fun getServicesApiCall(): MutableLiveData<Data> {

        val call = RetrofitClient.apiInterface.getFeed()

        call.enqueue(object: Callback<NewsScreenModel> {
            override fun onFailure(call: Call<NewsScreenModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<NewsScreenModel>,
                response: Response<NewsScreenModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceSetterGetter.value = msg
            }
        })

        return serviceSetterGetter as MutableLiveData<Data>
    }

    fun getArticalDetailsApiCall(articalId: String?): MutableLiveData<ArticalData> {

        val call = RetrofitClient.apiInterface.getArticalDetail(articalId)

        call.enqueue(object: Callback<ArticalDataModel> {
            override fun onFailure(call: Call<ArticalDataModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<ArticalDataModel>,
                response: Response<ArticalDataModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceSetterGetterArticalDetail.value = msg
            }
        })

        return serviceSetterGetterArticalDetail as MutableLiveData<ArticalData>
    }

    fun getInterestsApiCall(): MutableLiveData<InterestsData> {

        val call = RetrofitClient.apiInterface.getInterests()

        call.enqueue(object: Callback<InterestsModel> {
            override fun onFailure(call: Call<InterestsModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<InterestsModel>,
                response: Response<InterestsModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceSetterGetterInterests.value = msg
            }
        })

        return serviceSetterGetterInterests as MutableLiveData<InterestsData>
    }

    fun postLoginData(request: LoginPostData): MutableLiveData<LoginModel> {

        val call = RetrofitClient.apiInterface.createLogin(request)

        call.enqueue(object: Callback<LoginModel> {
            override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LoginModel>,
                response: Response<LoginModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data
                loginData.value = msg
            }
        })

        return loginData as MutableLiveData<LoginModel>
    }

    fun getTrendingApiCall(): MutableLiveData<TrendingData> {

        val call = RetrofitClient.apiInterface.getTrendingData()

        call.enqueue(object: Callback<TrendingDataModel> {
            override fun onFailure(call: Call<TrendingDataModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<TrendingDataModel>,
                response: Response<TrendingDataModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceTrendingSetterGetter.value = msg
            }
        })

        return serviceTrendingSetterGetter as MutableLiveData<TrendingData>
    }

    fun getFeaturedApiCall(): MutableLiveData<FuturedData> {

        val call = RetrofitClient.apiInterface.getFeaturedData()

        call.enqueue(object: Callback<FuturedDataModel> {
            override fun onFailure(call: Call<FuturedDataModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<FuturedDataModel>,
                response: Response<FuturedDataModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceFeaturedSetterGetter.value = msg
            }
        })

        return serviceFeaturedSetterGetter as MutableLiveData<FuturedData>
    }

    fun getLiveNewsApiCall(): MutableLiveData<LiveNewsData> {

        val call = RetrofitClient.apiInterface.getLiveNews()

        call.enqueue(object: Callback<LiveNewsModel> {
            override fun onFailure(call: Call<LiveNewsModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<LiveNewsModel>,
                response: Response<LiveNewsModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceSetterGetterLiveNews.value = msg
            }
        })

        return serviceSetterGetterLiveNews as MutableLiveData<LiveNewsData>
    }

    fun getPodcastsApiCall(): MutableLiveData<PodcastsData> {

        val call = RetrofitClient.apiInterface.getPodcasts()

        call.enqueue(object: Callback<PodcastsDataModel> {
            override fun onFailure(call: Call<PodcastsDataModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<PodcastsDataModel>,
                response: Response<PodcastsDataModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data?.data
                serviceSetterGetterPodcasts.value = msg
            }
        })

        return serviceSetterGetterPodcasts as MutableLiveData<PodcastsData>
    }
    fun postMobileOTPRequest(request: MobileOTPRequestPostModel): MutableLiveData<MobileOTPRequestResponseModel> {

        val call = RetrofitClient.apiInterface.sendOTP(request)

        call.enqueue(object: Callback<MobileOTPRequestResponseModel> {
            override fun onFailure(call: Call<MobileOTPRequestResponseModel>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<MobileOTPRequestResponseModel>,
                response: Response<MobileOTPRequestResponseModel>
            ) {
                Log.v("DEBUG : ", response.body().toString())
                val data = response.body()
                val msg = data
                mobileOtpReq.value = msg
            }
        })

        return mobileOtpReq as MutableLiveData<MobileOTPRequestResponseModel>
    }
}