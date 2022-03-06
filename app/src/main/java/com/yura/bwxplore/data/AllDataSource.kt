package com.yura.bwxplore.data

import androidx.lifecycle.LiveData
import com.yura.bwxplore.data.firebase.entities.Location

interface AllDataSource {
    fun getAllPlaces(): LiveData<List<Location>>
    fun getPopularPlaces(): LiveData<List<Location>>
}