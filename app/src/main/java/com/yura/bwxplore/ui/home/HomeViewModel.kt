package com.yura.bwxplore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.yura.bwxplore.data.firebase.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {
    fun getAllPlaces() = repository.getAllPlaces()
    fun getPopularPlaces() = repository.getPopularPlaces()
}