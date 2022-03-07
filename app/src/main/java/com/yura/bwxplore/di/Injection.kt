package com.yura.bwxplore.di

import android.content.Context
import com.yura.bwxplore.data.Repository
import com.yura.bwxplore.data.firebase.database.FirebaseDatabase
import com.yura.bwxplore.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context) : Repository {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        var remoteDataSource = RemoteDataSource.getInstance()
        return Repository.getInstance(firebaseDatabase, remoteDataSource)
    }
}