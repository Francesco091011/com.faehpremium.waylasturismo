package com.faehpremium.waylasturismo.data

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    val result: String,
    @SerializedName("base_code")
    val baseCode: String,
    val rates: Map<String, Double>,
    @SerializedName("time_last_update_utc")
    val lastUpdate: String
)
