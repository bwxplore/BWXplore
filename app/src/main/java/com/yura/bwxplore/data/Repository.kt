package com.yura.bwxplore.data

import androidx.lifecycle.LiveData
import com.yura.bwxplore.data.firebase.database.FirebaseDatabase
import com.yura.bwxplore.data.firebase.entities.Location
import com.yura.bwxplore.data.remote.RemoteDataSource
import com.yura.bwxplore.data.remote.entities.ArticlesItem

class Repository private  constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val remoteDataSource: RemoteDataSource
): AllDataSource{

    override fun getAllPlaces(): LiveData<List<Location>> {
        return firebaseDatabase.getAllPlaces()
    }

    override fun getPopularPlaces(): LiveData<List<Location>> {
        return firebaseDatabase.getPopularPlaces()
    }

    override fun getNews(): LiveData<List<ArticlesItem>> {
        return remoteDataSource.getNews()
    }

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(
            firebaseDatabase: FirebaseDatabase,
            remoteDataSource: RemoteDataSource
        ) : Repository =
            instance ?: synchronized(this)
            {
                instance ?: Repository(
                    firebaseDatabase,
                    remoteDataSource
                ).apply { instance = this }
            }
    }
}