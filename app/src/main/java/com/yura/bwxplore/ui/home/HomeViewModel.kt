package com.yura.bwxplore.ui.home

import androidx.lifecycle.ViewModel
import com.yura.bwxplore.data.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {
    fun getPopularPlaces() = repository.getPopularPlaces()
    fun getNews() = repository.getNews()
}