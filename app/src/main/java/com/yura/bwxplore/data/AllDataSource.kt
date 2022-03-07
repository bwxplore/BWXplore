package com.yura.bwxplore.data

import androidx.lifecycle.LiveData
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.data.remote.entities.ArticlesItem

interface AllDataSource {
    fun getAllPlaces(): LiveData<List<Location>>
    fun getPopularPlaces(): LiveData<List<Location>>
    fun getNews(): LiveData<List<ArticlesItem>>
}