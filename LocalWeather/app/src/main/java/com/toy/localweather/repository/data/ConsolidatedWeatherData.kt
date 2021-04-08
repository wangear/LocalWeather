package com.toy.localweather.repository.data

import com.google.gson.annotations.SerializedName

data class ConsolidatedWeatherData(
        val id: String,
        @SerializedName("applicable_date") val applicableDate: String,
        @SerializedName("weather_state_name") val weatherStateName: String,
        @SerializedName("weather_state_abbr") val weatherStateAbbr: String,
        @SerializedName("the_temp") val theTemp: Double,
        val humidity: String
)
