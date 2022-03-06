package com.yura.bwxplore.data.firebase.entities

data class Location(
    var id: Int? = null,
    var name: String = "",
    var lat: Float? = null,
    var long: Float? = null,
    var imageUrl: String = "",
    var popular: Boolean = false
)
