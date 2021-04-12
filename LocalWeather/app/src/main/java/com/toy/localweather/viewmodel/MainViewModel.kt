package com.toy.localweather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toy.localweather.Constants
import com.toy.localweather.repository.Repository
import com.toy.localweather.repository.data.LocationResData
import com.toy.localweather.repository.data.LocationSearchResData
import com.toy.localweather.repository.model.LocalWeatherRecyclerModel
import com.toy.localweather.repository.model.LocalWeatherRecyclerModelMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private var repository: Repository) : ViewModel() {
    private val _weatherList = MutableLiveData<ArrayList<LocalWeatherRecyclerModel>>()
    val weatherList: LiveData<ArrayList<LocalWeatherRecyclerModel>>
        get() = _weatherList
    private val _progress = MutableLiveData<Boolean>()
    val progress: LiveData<Boolean>
        get() = _progress

    private val localArrayList: ArrayList<LocalWeatherRecyclerModel> = ArrayList()

    init {
        _progress.value = true
        getWeatherData()
    }


    fun getWeatherData() {
        viewModelScope.launch {
            localArrayList.clear()
            _weatherList.value = localArrayList

            val localSearchJob = async(Dispatchers.IO) {
                requestLocaleSearch(Constants.LOCALE_VALUE)
            }

            localSearchJob.await()?.let { list ->
                val locationSearchJob = launch(Dispatchers.IO) {

                    list.forEach { locationSearchResData ->
                        launch(Dispatchers.IO) {
                            requestWeatherByLocationWithLocationSearch(locationSearchResData)
                        }
                    }
                }
                locationSearchJob.join()
                // 업데이트
                _progress.postValue(false)
                _weatherList.postValue(localArrayList)
            }
        }
    }


    private suspend fun requestWeatherByLocationWithLocationSearch(locationSearchResData: LocationSearchResData) {
        val locationResData =
                requestWeatherByLocation(locationSearchResData.woeid)

        locationResData?.let { it ->
            val itemData = LocalWeatherRecyclerModelMapper.from(
                    it,
                    locationSearchResData
            )

            itemData?.let {
                localArrayList.add(itemData)
            }
        }
    }

    private suspend fun requestLocaleSearch(locale: String): List<LocationSearchResData>? {
        var response = repository.requestLocationSearch(locale)
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
        }
        // 요청 실패
        return null
    }

    private suspend fun requestWeatherByLocation(wodId: String): LocationResData? {
        var response = repository.requestWeatherByLocation(wodId)
        if (response.isSuccessful) {
            response.body()?.let {
                return it
            }
        }
        // 요청 실패
        return null
    }
}
