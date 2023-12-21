package com.example.hse_android_lab_3.retrofit

import retrofit2.http.GET

interface NewsApi {
    @GET("api/1/news?apikey=pub_35139e48bd5ceeb93e6e3b5c61a61a636ed0d&language=en")
    suspend fun getNews(): NewsResponse
}