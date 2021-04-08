package com.toy.localweather.repository.model

import com.toy.localweather.repository.data.LocationResData
import com.toy.localweather.repository.data.LocationSearchResData

data class LocalWeatherRecyclerModel(
        var local: String,
        var today: LocationWeatherModel,
        var tomorrow: LocationWeatherModel
)

object LocalWeatherRecyclerModelMapper {
    fun from(
            locationRes: LocationResData,
            locationSearchRes: LocationSearchResData
    ): LocalWeatherRecyclerModel? {
        locationRes.consolidatedWeatherList.let { list ->
            if (list.size > 2) {
                val today = LocationWeatherModelMapper.from(list[0])
                val tomorrow = LocationWeatherModelMapper.from(list[1])
                return LocalWeatherRecyclerModel(locationSearchRes.title, today, tomorrow)
            }
        }
        return null
    }
}
