package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccommodationsScreen() {
    val hotels = listOf("Hotel Huascarán", "Selina Huaraz", "The Waymans House", "Akira Hostal")
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Alojamientos Recomendados", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        hotels.forEach { hotel ->
            Card(elevation = 2.dp, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Text(hotel, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}
