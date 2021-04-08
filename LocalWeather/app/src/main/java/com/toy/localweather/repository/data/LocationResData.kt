package com.toy.localweather.repository.data

import com.google.gson.annotations.SerializedName

data class LocationResData(
        val title: String,
        @SerializedName("location_type") val locationType: String,
        @SerializedName("latt_long") val lattLong: String,
        val time: String,
        @SerializedName("timezone_name") val timezoneName: String,
        @SerializedName("consolidated_weather") val consolidatedWeatherList: List<ConsolidatedWeatherData>
)
