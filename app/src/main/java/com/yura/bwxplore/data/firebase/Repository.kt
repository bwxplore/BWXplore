package com.yura.bwxplore.data.firebase

import androidx.lifecycle.LiveData
import com.yura.bwxplore.data.AllDataSource
import com.yura.bwxplore.data.firebase.database.FirebaseDatabase
import com.yura.bwxplore.data.firebase.entities.Location

class Repository private  constructor(
    private val firebaseDatabase: FirebaseDatabase
): AllDataSource{

    override fun getAllPlaces(): LiveData<List<Location>> {
        return firebaseDatabase.getAllPlaces()
    }

    override fun getPopularPlaces(): LiveData<List<Location>> {
        return firebaseDatabase.getPopularPlaces()
    }

    companion object{
        @Volatile
        private var instance : Repository? = null

        fun getInstance(
            firebaseDatabase: FirebaseDatabase
        ) : Repository =
            instance?: synchronized(this)
            {
                instance?:Repository(
                    firebaseDatabase
                ).apply { instance= this }
            }
    }
}