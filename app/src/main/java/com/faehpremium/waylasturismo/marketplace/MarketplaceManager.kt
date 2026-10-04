package com.faehpremium.waylasturismo.marketplace

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ServiceListing(
    val id: String,
    val title: String,
    val provider: String,
    val category: String,
    val pricePerDay: Double,
    val rating: Float,
    val description: String,
    val location: String
)

data class Reservation(
    val reservationId: String,
    val serviceId: String,
    val serviceTitle: String,
    val provider: String,
    val date: String,
    val travelerName: String,
    val participantsCount: Int,
    val totalPrice: Double,
    val status: ReservationStatus = ReservationStatus.CONFIRMED
)

enum class ReservationStatus {
    PENDING, CONFIRMED, COMPLETED, CANCELLED
}

class MarketplaceManager {
    private val _listings = listOf(
        ServiceListing(
            id = "serv_1",
            title = "Guía Oficial de Alta Montaña UIAGM",
            provider = "Huaraz Mountain Guides",
            category = "Guiado",
            pricePerDay = 150.0,
            rating = 4.9f,
            description = "Guía certificado con más de 10 años de experiencia en cumbres superiores a 5,000m.",
            location = "Huaraz & Cordillera Blanca"
        ),
        ServiceListing(
            id = "serv_2",
            title = "Alquiler de Equipo Completo de Trekking",
            provider = "Andean Gear Rental",
            category = "Equipamiento",
            pricePerDay = 45.0,
            rating = 4.7f,
            description = "Incluye bastones, carpa de alta montaña, bolsa de dormir -10°C y crampones.",
            location = "Av. Luzuriaga 400, Huaraz"
        ),
        ServiceListing(
            id = "serv_3",
            title = "Transporte Privado 4x4 a Lagunas y Cañones",
            provider = "Waylas Express Tours",
            category = "Transporte",
            pricePerDay = 200.0,
            rating = 4.8f,
            description = "Camioneta 4x4 con chofer experto en rutas altoandinas y oxígeno abordo.",
            location = "Salidas desde Huaraz"
        )
    )

    val listings: List<ServiceListing> get() = _listings

    private val _reservations = MutableStateFlow<List<Reservation>>(emptyList())
    val reservations: StateFlow<List<Reservation>> = _reservations.asStateFlow()

    fun createReservation(
        serviceId: String,
        date: String,
        travelerName: String,
        participantsCount: Int
    ): Reservation? {
        val service = _listings.find { it.id == serviceId } ?: return null
        val total = service.pricePerDay * participantsCount
        val reservationId = "RES-${System.currentTimeMillis().toString().takeLast(6)}"

        val reservation = Reservation(
            reservationId = reservationId,
            serviceId = service.id,
            serviceTitle = service.title,
            provider = service.provider,
            date = date,
            travelerName = travelerName,
            participantsCount = participantsCount,
            totalPrice = total,
            status = ReservationStatus.CONFIRMED
        )

        val currentList = _reservations.value.toMutableList()
        currentList.add(reservation)
        _reservations.value = currentList

        return reservation
    }

    fun cancelReservation(reservationId: String): Boolean {
        val currentList = _reservations.value.map {
            if (it.reservationId == reservationId) {
                it.copy(status = ReservationStatus.CANCELLED)
            } else {
                it
            }
        }
        _reservations.value = currentList
        return true
    }
}
