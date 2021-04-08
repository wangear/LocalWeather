package com.toy.localweather.repository

import com.toy.localweather.repository.data.LocationResData
import com.toy.localweather.repository.data.LocationSearchResData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("/api/location/search/")
    suspend fun requestLocationSearch(@Query(value = "query", encoded = true) query: String
    ): Response<List<LocationSearchResData>>

    @GET("/api/location/{woeid}")
    suspend fun requestWeatherByLocation(@Path("woeid") woeid: String
    ): Response<LocationResData>
}