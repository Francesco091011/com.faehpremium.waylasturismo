package com.faehpremium.waylasturismo.premium

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class SubscriptionPlan(val title: String, val price: String, val durationMonths: Int) {
    MONTHLY("Waylas Pro Mensual", "$9.99 / mes", 1),
    ANNUAL("Waylas Pro Anual (Ahorra 40%)", "$59.99 / ano", 12),
    LIFETIME("Acceso Vitalicio VIP", "$149.99 unico", 999)
}

class PremiumManager {
    private val _isPremium = MutableStateFlow(false)
    val isPremium: StateFlow<Boolean> = _isPremium.asStateFlow()

    private val _activePlan = MutableStateFlow<SubscriptionPlan?>(null)
    val activePlan: StateFlow<SubscriptionPlan?> = _activePlan.asStateFlow()

    private val _expiryTimestamp = MutableStateFlow<Long>(0L)
    val expiryTimestamp: StateFlow<Long> = _expiryTimestamp.asStateFlow()

    fun subscribe(plan: SubscriptionPlan) {
        _isPremium.value = true
        _activePlan.value = plan
        val monthsMillis = plan.durationMonths * 30L * 24L * 60L * 60L * 1000L
        _expiryTimestamp.value = System.currentTimeMillis() + monthsMillis
    }

    fun cancelSubscription() {
        _isPremium.value = false
        _activePlan.value = null
        _expiryTimestamp.value = 0L
    }

    fun hasFeatureAccess(featureName: String): Boolean {
        // Si es usuario premium, tiene acceso a todo
        if (_isPremium.value) return true

        // Funciones gratuitas permitidas
        val freeFeatures = listOf("destinations", "map_basic", "weather_current", "tips")
        return freeFeatures.contains(featureName)
    }
}
