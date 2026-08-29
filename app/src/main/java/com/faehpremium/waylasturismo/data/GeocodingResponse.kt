package com.faehpremium.waylasturismo.data

import com.google.gson.annotations.SerializedName

data class GeocodingResponse(
    val results: List<GeocodingResult>?
)

data class GeocodingResult(
    val id: Long,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val country: String,
    @SerializedName("admin1")
    val adminArea: String?
)
