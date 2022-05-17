package com.yura.bwxplore.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.maps.DirectionsApi
import com.google.maps.DirectionsApiRequest
import com.google.maps.GeoApiContext
import com.google.maps.GeoApiContext.*
import com.yura.bwxplore.BuildConfig.*
import com.yura.bwxplore.data.remote.entities.CalculateResponse
import kotlinx.coroutines.awaitAll
import okhttp3.*
import java.io.IOException


class MapsViewModel : ViewModel(){
    private val _calculate = MutableLiveData<CalculateResponse>()
    val calculate: LiveData<CalculateResponse> = _calculate

    var status = MutableLiveData<Boolean?>()

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun postValue(value: String) {
        _isLoading.postValue(true)
        //creating a client
        val okHttpClient = OkHttpClient()
        // building a request
        val formBody: RequestBody = FormBody.Builder()
            .add("value", value)
            .build()

        val request: Request = Request.Builder().url("https://bwxplore.herokuapp.com/calculate").post(formBody).build()

        // making call asynchronously
        okHttpClient.newCall(request).enqueue(object : Callback {
            // called if server is unreachable
            override fun onFailure(call: Call, e: IOException) {
                _isLoading.postValue(false)
                status.postValue(false)
                Log.e("ServerError", "Error Connect to Server")
            }

            @Throws(IOException::class)
            override fun onResponse(
                call: Call,
                response: Response
            ) {
                _isLoading.postValue(false)
                status.postValue(true)
                val gson = Gson()
                val responses = response.body?.string()
                _calculate.postValue(gson.fromJson(responses, CalculateResponse::class.java))
            }
        })
    }
}