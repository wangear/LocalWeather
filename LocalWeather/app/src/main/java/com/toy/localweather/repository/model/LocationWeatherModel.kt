package com.toy.localweather.repository.model

import com.toy.localweather.Constants
import com.toy.localweather.repository.data.ConsolidatedWeatherData

data class LocationWeatherModel(
        val applicableDate: String,
        val weatherStateName: String,
        val weatherStateUrl: String,
        val theTemp: Double,
        val humidity: String
)

object LocationWeatherModelMapper {
    fun from(from: ConsolidatedWeatherData) = LocationWeatherModel(
            from.applicableDate,
            from.weatherStateName,
            Constants.IMAGE_PREFIX + from.weatherStateAbbr + Constants.IMAGE_SUFFIX,
            from.theTemp,
            from.humidity
    )
}
