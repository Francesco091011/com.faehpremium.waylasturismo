package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdventureSportsScreen() {
    // Ejemplo de lugares y deportes de aventura en Áncash
    val deportes = listOf(
        "Parapente en Huaraz",
        "Canotaje en el río Santa",
        "Escalada en roca en Hatun Machay",
        "Trekking a la Laguna 69",
        "Mountain bike en la Cordillera Blanca",
        "Sandboard en las dunas de Sechín",
        "Bungee Jumping (próximamente)",
        "Cañoning en Caraz"
    )
    Scaffold(
        topBar = { TopAppBar(title = { Text("Deportes de Aventura") }) },
        content = { padding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(deportes) { deporte ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        elevation = 4.dp
                    ) {
                        Text(
                            text = deporte,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }
    )
}
