package com.yura.bwxplore.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yura.bwxplore.data.Repository
import com.yura.bwxplore.di.Injection
import com.yura.bwxplore.ui.home.HomeViewModel

class ViewModelFactory private constructor(private val repository: Repository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when
        {
            modelClass.isAssignableFrom(HomeViewModel::class.java) ->
            {
                return HomeViewModel(repository) as T
            }
            else -> {
                throw Throwable("Unknown Viewmodel Class : ${modelClass.name}")
            }
        }
    }
    companion object{
        @Volatile
        private var instance :ViewModelFactory? = null

        fun getInstance(context: Context) : ViewModelFactory =
            instance ?: synchronized(this)
            {
                instance?: ViewModelFactory(
                    Injection.provideRepository(context)
                )
            }
    }
}