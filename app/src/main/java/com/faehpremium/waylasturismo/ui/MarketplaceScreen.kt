package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faehpremium.waylasturismo.marketplace.MarketplaceManager

@Composable
fun MarketplaceScreen() {
    val marketplaceManager = remember { MarketplaceManager() }
    var bookingMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Handshake, contentDescription = "Marketplace", tint = MaterialTheme.colors.primary, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Marketplace de Servicios", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        Text("Reserva guías oficiales, equipo de montaña y transporte privado en Huaraz.", fontSize = 14.sp, color = Color.Gray)

        if (bookingMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = Color(0xFFE3F2FD),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.BookmarkAdded, contentDescription = null, tint = Color(0xFF1976D2))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(bookingMessage ?: "", color = Color(0xFF0D47A1), fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Servicios Disponibles", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(marketplaceManager.listings) { listing ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(listing.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f))
                            Text("★ ${listing.rating}", fontWeight = FontWeight.Bold, color = Color(0xFFF57C00))
                        }
                        Text("Proveedor: ${listing.provider} | ${listing.location}", fontSize = 13.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(listing.description, fontSize = 14.sp, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("$${listing.pricePerDay} / día", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary, fontSize = 16.sp)
                            Button(onClick = {
                                val res = marketplaceManager.createReservation(
                                    serviceId = listing.id,
                                    date = "2026-10-15",
                                    travelerName = "Viajero Waylas",
                                    participantsCount = 2
                                )
                                if (res != null) {
                                    bookingMessage = "¡Reserva #${res.reservationId} creada para ${res.serviceTitle}!"
                                }
                            }) {
                                Text("Reservar Ahora")
                            }
                        }
                    }
                }
            }
        }
    }
}
