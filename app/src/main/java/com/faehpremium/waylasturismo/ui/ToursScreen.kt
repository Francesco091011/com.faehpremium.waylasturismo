package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faehpremium.waylasturismo.model.Tour

@Composable
fun ToursScreen() {
    val tours = listOf(
        Tour("1", "Glaciar Pastoruri", "S/ 60", "7 horas", "Ruta del cambio climático y puyas Raimondi."),
        Tour("2", "Laguna 69", "S/ 50", "12 horas", "Trekking exigente a una laguna azul turquesa."),
        Tour("3", "Lagunas de Llanganuco", "S/ 40", "8 horas", "Paseo en bote y vistas al Huascarán."),
        Tour("4", "Chavín de Huántar", "S/ 45", "9 horas", "Visita al templo milenario y sus galerías.")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Catálogo de Tours",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(tours) { tour ->
            TourItem(tour)
        }
    }
}

@Composable
fun TourItem(tour: Tour) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = tour.name, style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
                Text(text = tour.price, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.secondary)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "🕒 ${tour.duration}", style = MaterialTheme.typography.caption)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = tour.description, style = MaterialTheme.typography.body2)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { /* TODO: Implementar reserva */ },
                modifier = Modifier.align(androidx.compose.ui.Alignment.End)
            ) {
                Text("Reservar Ahora")
            }
        }
    }
}
