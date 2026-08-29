package com.faehpremium.waylasturismo.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faehpremium.waylasturismo.data.WeatherApi
import com.faehpremium.waylasturismo.data.WeatherResponse
import com.faehpremium.waylasturismo.data.GeocodingResult
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _weatherState = mutableStateOf<WeatherResponse?>(null)
    val weatherState: State<WeatherResponse?> = _weatherState

    private val _searchResults = mutableStateOf<List<GeocodingResult>>(emptyList())
    val searchResults: State<List<GeocodingResult>> = _searchResults

    private val _currentCityName = mutableStateOf("Huaraz")
    val currentCityName: State<String> = _currentCityName

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val weatherApi = WeatherApi.create()

    fun fetchWeather(lat: Double, lon: Double, cityName: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _currentCityName.value = cityName
            try {
                val response = weatherApi.getStatus(lat, lon)
                _weatherState.value = response
                _searchResults.value = emptyList() // Limpiar búsqueda al seleccionar
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchCity(name: String) {
        if (name.length < 3) return
        viewModelScope.launch {
            try {
                val response = weatherApi.searchPlace(name)
                _searchResults.value = response.results ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
