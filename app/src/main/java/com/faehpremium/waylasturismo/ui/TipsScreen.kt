package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TipsScreen() {
    val scrollState = rememberScrollState()
    val tips = listOf(
        "Aclimátate" to "Pasa al menos un día en Huaraz antes de realizar caminatas de gran altitud.",
        "Hidratación" to "Bebe mucha agua y té de coca para combatir el mal de altura (soroche).",
        "Vestimenta" to "Vístete en capas. El clima puede cambiar drásticamente en minutos.",
        "Protección" to "Usa protector solar y lentes de sol; la radiación UV es muy alta en las montañas.",
        "Respeto" to "No dejes basura en los senderos y respeta la fauna y flora local."
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp).verticalScroll(scrollState)
    ) {
        Text("Consejos para tu viaje", style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        
        tips.forEach { (title, desc) ->
            Card(
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Lightbulb, contentDescription = null, tint = MaterialTheme.colors.secondary)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(title, style = MaterialTheme.typography.subtitle1, color = MaterialTheme.colors.primary)
                        Text(desc, style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}
