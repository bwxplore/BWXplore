package com.yura.bwxplore.data.firebase.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.yura.bwxplore.data.firebase.entities.Location

class FirebaseDatabase {

    private val db = FirebaseFirestore.getInstance()

    fun getAllPlaces(): LiveData<List<Location>> {
        val places = MutableLiveData<List<Location>>()
        return try {
            db.collection("places")
                .get()
                .addOnSuccessListener {
                    val res = it.toObjects(Location::class.java)
                    val result = ArrayList<Location>()
                    result.addAll(res)
                    places.value = result
                }
            places
        } catch (e: Exception) {
            places.value = emptyList()
            places
        }
    }

    fun getPopularPlaces(): LiveData<List<Location>> {
        val places = MutableLiveData<List<Location>>()
        return try {
            db.collection("places")
                .whereEqualTo("popular", true)
                .get()
                .addOnSuccessListener {
                    val res = it.toObjects(Location::class.java)
                    val result = ArrayList<Location>()
                    result.addAll(res)
                    places.value = result
                }
            places
        } catch (e: Exception) {
            places.value = emptyList()
            places
        }
    }

    companion object {
        @Volatile
        private var instance: FirebaseDatabase? = null

        fun getInstance(): FirebaseDatabase =
            instance ?: synchronized(this)
            {
                instance ?: FirebaseDatabase()
            }
    }
}