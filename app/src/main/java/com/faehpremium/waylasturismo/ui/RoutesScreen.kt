package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faehpremium.waylasturismo.model.Route

@Composable
fun RoutesScreen() {
    val routes = listOf(
        Route("1", "Callejón de Huaylas", "3 días", "Fácil", "Recorrido por los pueblos al pie de la Cordillera Blanca."),
        Route("2", "Callejón de Conchucos", "4 días", "Media", "Viaje histórico por Chavín, Huari y Chacas."),
        Route("3", "Cordillera Huayhuash", "8-12 días", "Difícil", "Uno de los trekkings más bellos del mundo."),
        Route("4", "Circuito de Playa Áncash", "2 días", "Fácil", "Relajo en las playas de Casma y Huarmey.")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Rutas Turísticas Recomendadas",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(routes) { route ->
            RouteItem(route)
        }
    }
}

@Composable
fun RouteItem(route: Route) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = route.title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(text = "🕒 ${route.duration}", style = MaterialTheme.typography.caption)
                Text(text = "⛰️ ${route.difficulty}", style = MaterialTheme.typography.caption)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = route.description, style = MaterialTheme.typography.body2)
        }
    }
}
