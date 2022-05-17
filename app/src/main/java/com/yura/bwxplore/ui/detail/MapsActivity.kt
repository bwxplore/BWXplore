package com.yura.bwxplore.ui.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.DirectionsApiRequest
import com.google.maps.GeoApiContext
import com.yura.bwxplore.BuildConfig
import com.yura.bwxplore.MainActivity
import com.yura.bwxplore.R
import com.yura.bwxplore.data.LocationData
import com.yura.bwxplore.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var id: String? = null
    private var values: String? = null
    private lateinit var binding: ActivityMapsBinding
    private val mapsViewModel: MapsViewModel by viewModels()
    private val places = LocationData.listData
    lateinit var idLists: List<Int>

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        id = intent.getStringExtra(ID)
        values = intent.getStringExtra(VALUE)

        val listId = idToList(id!!.dropLast(1))

        idLists = listId

        mapsViewModel.apply {
            binding.apply {
                postValue(values!!)

                btnBack.setOnClickListener {
                    startActivity(Intent(this@MapsActivity, MainActivity::class.java))
                }

                status.observe(this@MapsActivity) {
                    val status = if (it!!) "Load Successful" else "Error Fetching Data"
                    Toast.makeText(this@MapsActivity, status, Toast.LENGTH_SHORT).show()
                }

                isLoading.observe(this@MapsActivity) {
                    binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
                }

                calculate.observe(this@MapsActivity) {
                    val paths = StringBuilder()
                    for (i in it.path.indices) {
                        paths.append(places[listId[it.path[i]]].name)
                        paths.append(" -> ")
                    }
                    Log.d("tesplace", paths.toString())
                    tvResult.text = "Cost: ${
                        it.cost.toString().subSequence(0, 5)
                    } KM\nPath: ${paths.dropLast(4)}"
                }
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val lat = mutableListOf<Double>()
        val lon = mutableListOf<Double>()

        mapsViewModel.calculate.observe(this) {
            for (i in it.path.indices) {
                lat.add(places[idLists[it.path[i]]].lat!!)
                lon.add(places[idLists[it.path[i]]].long!!)
            }

//            for(i in it.path.indices-1){
//                getRoutes(
//                    "${places[idLists[it.path[i]]].lat!!},${places[idLists[it.path[i]]].long!!}",
//                    "${places[idLists[it.path[i + 1]]].lat!!},${places[idLists[it.path[i + 1]]].long!!}"
//                )
//            }

            for ((i, _) in lat.withIndex()) {
                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(lat[i], lon[i]))
                        .title(places[idLists[it.path[i]]].name)
                )?.showInfoWindow()
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat[0], lon[0]), 10.0F))
        }
    }

    var path = ArrayList<LatLng>()

    private fun getRoutes(origin: String, destination: String) {
        runOnUiThread {
        val context: GeoApiContext = GeoApiContext.Builder()
            .apiKey(BuildConfig.MAPS_API_KEY)
            .build()
        val req: DirectionsApiRequest =
            DirectionsApi.getDirections(context, origin, destination)

        try {
            val res = req.await()
            //Loop through legs and steps to get encoded polylines of each step
            if (res.routes != null && res.routes.isNotEmpty()) {
                val route = res.routes[0]
                if (route.legs != null) {
                    for (i in route.legs.indices) {
                        val leg = route.legs[i]
                        if (leg.steps != null) {
                            for (j in leg.steps.indices) {
                                val step = leg.steps[j]
                                if (step.steps != null && step.steps.isNotEmpty()) {
                                    for (k in step.steps.indices) {
                                        val step1 = step.steps[k]
                                        val points1 = step1.polyline
                                        if (points1 != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            val coords1 = points1.decodePath()
                                            for (coord1 in coords1) {
                                                path.add(LatLng(coord1.lat, coord1.lng))
                                            }
                                        }
                                    }
                                } else {
                                    val points = step.polyline
                                    if (points != null) {
                                        //Decode polyline and add points to list of route coordinates
                                        val coords = points.decodePath()
                                        for (coord in coords) {
                                            path.add(LatLng(coord.lat, coord.lng))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ex: Exception) {
            ex.localizedMessage?.let { Log.e(TAG, it) }
        }

            if (path.size > 0) {
                val opts = PolylineOptions().addAll(path).color(Color.BLUE).width(5f)
                mMap.addPolyline(opts)
            }
        }
    }

//    private fun getPath(): MutableLiveData<ArrayList<LatLng>> {
//        val x = MutableLiveData<ArrayList<LatLng>>()
//        val y:ArrayList<LatLng> = path
//        x.value = y
//        return x
//    }

    private fun idToList(id: String): List<Int> =
        id
            .split(",")
            .map { it.toInt() }

    companion object {
        const val VALUE = "value"
        const val ID = "id"
        const val TAG = "Maps"
    }
}