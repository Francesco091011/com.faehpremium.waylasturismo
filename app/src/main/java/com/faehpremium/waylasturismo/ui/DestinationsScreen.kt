package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faehpremium.waylasturismo.R

data class Destination(
    val name: String,
    val location: String,
    val description: String,
    val weather: String,
    val tours: List<String>,
    val comments: List<String>,
    val lat: Double,
    val lon: Double,
    val imageLabel: String = "Imagen",
    val imageRes: Int
)

@Composable
fun DestinationsScreen(onNavigateToMap: (Double, Double, String) -> Unit) {
    val destinations = listOf(
        Destination(
            name = "Laguna 69",
            location = "Parque Nacional Huascarán, Áncash",
            description = "La Laguna 69 es uno de los destinos más famosos de la Cordillera Blanca, ideal para trekking y fotografía. Altitud: 4,600 msnm.",
            weather = "12°C, soleado",
            tours = listOf("Full Day Laguna 69 (desde Huaraz)", "Trekking guiado con transporte y box lunch"),
            comments = listOf("\"Una experiencia inolvidable, paisajes espectaculares.\" - Ana", "\"Requiere buen estado físico, pero vale la pena.\" - Luis"),
            lat = -9.0033,
            lon = -77.6133,
            imageLabel = "Imagen Laguna 69",
            imageRes = R.drawable.laguna69
        ),
        Destination(
            name = "Chavín de Huántar",
            location = "Provincia de Huari, Áncash",
            description = "Centro arqueológico milenario, Patrimonio de la Humanidad, famoso por su laberinto de galerías subterráneas y la famosa 'Lanzón'.",
            weather = "15°C, parcialmente nublado",
            tours = listOf("Tour guiado a Chavín", "Visita arqueológica y cultural"),
            comments = listOf("\"Impresionante historia y arquitectura.\" - Pedro", "\"Un viaje al pasado preincaico.\" - María"),
            lat = -9.5931,
            lon = -77.1772,
            imageLabel = "Imagen Chavín",
            imageRes = R.drawable.chavinhuantar
        ),
        Destination(
            name = "Nevado Pastoruri",
            location = "Cordillera Blanca, Áncash",
            description = "Montaña icónica para caminatas en hielo, avistamiento de puyas Raimondi y paisajes glaciares.",
            weather = "5°C, frío y soleado",
            tours = listOf("Full Day Pastoruri", "Trekking y observación de puyas"),
            comments = listOf("\"Ver el glaciar fue increíble.\" - Sofía", "\"Ideal para fotos y naturaleza.\" - Diego"),
            lat = -9.8800,
            lon = -77.2100,
            imageLabel = "Imagen Pastoruri",
            imageRes = R.drawable.nevadopastoruri
        ),
        Destination(
            name = "Cañón del Pato",
            location = "Entre Caraz y Huallanca, Áncash",
            description = "Impresionante cañón con túneles y vistas espectaculares, ideal para ciclismo y fotografía.",
            weather = "18°C, soleado",
            tours = listOf("Ciclismo por el cañón", "Tour fotográfico"),
            comments = listOf("\"Aventura y paisajes únicos.\" - Juan", "\"Recomiendo el recorrido en bicicleta.\" - Elena"),
            lat = -8.9667,
            lon = -77.7833,
            imageLabel = "Imagen Cañón del Pato",
            imageRes = R.drawable.canonpato
        )
    )

    val expandedIndex = remember { mutableStateOf(-1) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        destinations.forEachIndexed { idx, dest ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = 6.dp
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = dest.imageRes),
                            contentDescription = dest.imageLabel,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(MaterialTheme.shapes.small),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(dest.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                            Text(dest.location, fontSize = 14.sp)
                        }
                        IconButton(onClick = { expandedIndex.value = if (expandedIndex.value == idx) -1 else idx }) {
                            Icon(
                                imageVector = if (expandedIndex.value == idx) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = "Expandir"
                            )
                        }
                    }
                    if (expandedIndex.value == idx) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(dest.description, fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        var isAdded by remember { mutableStateOf(false) }
                        var isShared by remember { mutableStateOf(false) }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                            Button(onClick = { isAdded = true }) {
                                Text(if (isAdded) "Agregado" else "Agregar a mi itinerario")
                            }
                            Button(onClick = { onNavigateToMap(dest.lat, dest.lon, dest.name) }) {
                                Text("Ver en mapa")
                            }
                            Button(onClick = { isShared = true }) {
                                Text("Compartir")
                            }
                        }
                        if (isAdded) {
                            Text("¡Destino agregado a tu itinerario!", color = Color(0xFF388E3C))
                        }
                        if (isShared) {
                            Text("¡Comparte tu experiencia con tus amigos!", color = Color(0xFF1976D2))
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Clima actual: ${dest.weather}", fontSize = 15.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Tours disponibles:", fontWeight = FontWeight.Bold)
                        dest.tours.forEach { Text("- $it") }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Comentarios de viajeros:", fontWeight = FontWeight.Bold)
                        dest.comments.forEach { Text(it) }
                    }
                }
            }
        }
    }
}
