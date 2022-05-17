package com.yura.bwxplore.data.remote.entities

import com.google.gson.annotations.SerializedName

data class CalculateResponse(

	@field:SerializedName("path")
	val path: List<Int>,

	@field:SerializedName("cost")
	val cost: Double
)
