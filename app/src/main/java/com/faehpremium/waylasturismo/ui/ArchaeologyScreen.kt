package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faehpremium.waylasturismo.model.ArchaeologicalSite

@Composable
fun ArchaeologyScreen() {
    val sites = listOf(
        ArchaeologicalSite("1", "Chavín de Huántar", "Centro ceremonial y religioso cuna de la cultura andina.", "Huari, Áncash"),
        ArchaeologicalSite("2", "Willcahuaín", "Complejo arquitectónico de la cultura Wari.", "Huaraz, Áncash"),
        ArchaeologicalSite("3", "Honcopampa", "Restos arqueológicos con impresionantes chullpas y plazas.", "Carhuaz, Áncash"),
        ArchaeologicalSite("4", "Sechín", "Templo de piedra con relieves de guerreros y sacrificios.", "Casma, Áncash"),
        ArchaeologicalSite("5", "Pañamarca", "Centro ceremonial moche con impresionantes murales.", "Nepeña, Áncash")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Centros Arqueológicos",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(sites) { site ->
            ArchaeologicalSiteItem(site)
        }
    }
}

@Composable
fun ArchaeologicalSiteItem(site: ArchaeologicalSite) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = site.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = site.location, style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = site.description, style = MaterialTheme.typography.body2)
        }
    }
}
