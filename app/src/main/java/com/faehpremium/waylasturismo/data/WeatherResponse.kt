package com.faehpremium.waylasturismo.data

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("current_weather")
    val currentWeather: CurrentWeather
)

data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    @SerializedName("weathercode")
    val weatherCode: Int,
    val time: String
)
