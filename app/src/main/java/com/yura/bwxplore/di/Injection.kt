package com.yura.bwxplore.di

import android.content.Context
import com.yura.bwxplore.data.firebase.Repository
import com.yura.bwxplore.data.firebase.database.FirebaseDatabase

object Injection {
    fun provideRepository(context: Context) : Repository {
        val firebaseDatabase = FirebaseDatabase.getInstance()
        return Repository.getInstance(firebaseDatabase)
    }
}