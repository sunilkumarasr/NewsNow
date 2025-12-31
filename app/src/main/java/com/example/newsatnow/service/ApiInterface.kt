package com.example.newsatnow.service

import com.example.example.ArticalDataModel
import com.example.example.InterestsModel
import com.example.example.LoginModel
import com.example.example.LoginPostData
import com.example.example.NewsScreenModel
import com.example.example.RegisterModel
import com.example.example.RegisterPostData
import com.example.example.FuturedDataModel
import com.example.example.LiveNewsModel
import com.example.example.MobileOTPRequestPostModel
import com.example.example.MobileOTPRequestResponseModel
import com.example.example.PodcastsDataModel
import com.example.example.TrendingDataModel
import com.example.newsatnow.model.Citys.CitysDataModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiInterface {

    @GET("api/articles/home")
    fun getFeed() : Call<NewsScreenModel>

    @GET("api/live-news/all")
    fun getLiveNews() : Call<LiveNewsModel>

    @GET("api/podcasts/all")
    fun getPodcasts() : Call<PodcastsDataModel>

    @GET("api/articles/trending?language=en&limit=10")
    fun getTrendingData() : Call<TrendingDataModel>

    @GET("api/articles/featured?language=en&limit=10")
    fun getFeaturedData() : Call<FuturedDataModel>

    @GET("api/interests/public")
    fun getInterests() : Call<InterestsModel>

    @GET("api/articles/details/{id}")
    fun getArticalDetail(@Path("id")articalId: String?) : Call<ArticalDataModel>

    @POST("api/login")
    fun createLogin(@Body request: LoginPostData) : Call<LoginModel>

    @POST("api/register")
    fun createRegister(@Body request: RegisterPostData) : Call<RegisterModel>

    @POST("api/send-otp")
    fun sendOTP(@Body request: MobileOTPRequestPostModel) : Call<MobileOTPRequestResponseModel>

    @GET("api/cities")
    fun getCitys() : Call<CitysDataModel>

}