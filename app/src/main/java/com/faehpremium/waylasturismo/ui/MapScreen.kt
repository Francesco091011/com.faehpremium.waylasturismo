package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MapScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(Icons.Default.Map, contentDescription = null, modifier = Modifier.size(100.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Mapa Interactivo (Próximamente)", style = MaterialTheme.typography.h6)
            Text("Aquí podrás ver los puntos de interés en Huaraz.", style = MaterialTheme.typography.body2)
        }
    }
}
