package com.toy.localweather.repository.data

import com.google.gson.annotations.SerializedName

data class LocationSearchResData(
        val title: String,
        @SerializedName("location_type") val locationType: String,
        @SerializedName("latt_long") val lattLong: String,
        val woeid: String
)
