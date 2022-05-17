package com.yura.bwxplore.ui.explore

import androidx.lifecycle.ViewModel
import com.yura.bwxplore.data.Repository

class ExploreViewModel(private val repository: Repository) : ViewModel() {
    fun getAllPlaces() = repository.getAllPlaces()
}