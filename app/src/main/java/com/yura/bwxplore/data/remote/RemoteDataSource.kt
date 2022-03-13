package com.yura.bwxplore.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yura.bwxplore.BuildConfig
import com.yura.bwxplore.data.remote.entities.ArticlesItem
import com.yura.bwxplore.data.remote.entities.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    companion object {
        const val API_KEY = BuildConfig.NEWS_API_KEY
        const val Q = "wisata+jawa+timur"
        const val SHORTBY = "publishedAt"

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun getNews(): LiveData<List<ArticlesItem>> {
        val resultNews = MutableLiveData<List<ArticlesItem>>()

        val client = ApiConfig.getApiService().getNews(Q, SHORTBY, API_KEY)
        client.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val result = ArrayList<ArticlesItem>()
                        responseBody.articles?.forEach {
                            it?.let { data -> result.add(data) }
                        }
                        resultNews.value = result
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getDetailTvShow onFailure : ${t.message}")
            }
        })
        return resultNews
    }
}