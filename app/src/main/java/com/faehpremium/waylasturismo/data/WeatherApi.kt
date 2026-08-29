package com.faehpremium.waylasturismo.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast")
    suspend fun getStatus(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse

    @GET("https://geocoding-api.open-meteo.com/v1/search")
    suspend fun searchPlace(
        @Query("name") name: String,
        @Query("count") count: Int = 5,
        @Query("language") language: String = "es",
        @Query("format") format: String = "json"
    ): GeocodingResponse

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/"

        fun create(): WeatherApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}
