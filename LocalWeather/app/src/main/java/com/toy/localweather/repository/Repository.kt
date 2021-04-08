package com.toy.localweather.repository

import com.toy.localweather.repository.data.LocationResData
import com.toy.localweather.repository.data.LocationSearchResData
import retrofit2.Response
import javax.inject.Inject


class Repository @Inject constructor(private val webservice: ApiInterface) {
    suspend fun requestLocationSearch(location: String): Response<List<LocationSearchResData>> {
        return webservice.requestLocationSearch(location)
    }

    suspend fun requestWeatherByLocation(wodId: String): Response<LocationResData> {
        return webservice.requestWeatherByLocation(wodId)
    }
}