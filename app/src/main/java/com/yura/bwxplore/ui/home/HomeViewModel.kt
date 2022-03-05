package com.yura.bwxplore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class HomeViewModel : ViewModel() {

    private lateinit var firebaseUser: FirebaseUser

    private val _text = MutableLiveData<String>().apply {
        value = getUserData()
    }
    val text: LiveData<String> = _text

    private fun getUserData(): String? {
        firebaseUser = FirebaseAuth.getInstance().currentUser!!
        return firebaseUser.displayName
    }
}