package com.yura.bwxplore.data.firebase.entities

data class Location(
    var id: Int? = null,
    var name: String = "",
    var lat: Double? = null,
    var long: Double? = null,
    var imageUrl: String = "",
    var popular: Boolean = false,
    var isChecked: Boolean = false
)
