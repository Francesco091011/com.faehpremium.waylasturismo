package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GastronomyScreen() {
    val dishes = listOf("Picante de Cuy", "Llunca Cashki", "Cuchicanca", "Pachamanca")
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Gastronomía Ancashina", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        dishes.forEach { dish ->
            Card(elevation = 2.dp, modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Text(dish, modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}
