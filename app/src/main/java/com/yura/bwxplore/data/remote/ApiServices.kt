package com.yura.bwxplore.data.remote

import com.yura.bwxplore.data.remote.entities.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("everything")
    fun getNews(
        @Query("q") q: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}