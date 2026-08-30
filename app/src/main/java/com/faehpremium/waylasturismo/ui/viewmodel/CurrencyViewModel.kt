package com.faehpremium.waylasturismo.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faehpremium.waylasturismo.data.CurrencyApi
import com.faehpremium.waylasturismo.data.CurrencyResponse
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val _currencyState = mutableStateOf<CurrencyResponse?>(null)
    val currencyState: State<CurrencyResponse?> = _currencyState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val currencyApi = CurrencyApi.create()

    init {
        fetchRates("USD")
    }

    fun fetchRates(base: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = currencyApi.getRates(base)
                _currencyState.value = response
            } catch (e: Exception) {
                _error.value = "Error al conectar con el servicio de divisas"
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
