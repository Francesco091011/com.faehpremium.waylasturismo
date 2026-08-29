package com.faehpremium.waylasturismo.ui

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faehpremium.waylasturismo.ui.viewmodel.WeatherViewModel
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(),
    onSelectOnMap: () -> Unit = {}
) {
    val context = LocalContext.current
    val weather = viewModel.weatherState.value
    val isLoading = viewModel.isLoading.value
    val searchResults = viewModel.searchResults.value
    val cityName = viewModel.currentCityName.value
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var searchQuery by remember { mutableStateOf("") }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permsMap: Map<String, Boolean> ->
        if (permsMap[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permsMap[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    viewModel.fetchWeather(it.latitude, it.longitude, "Mi ubicación")
                }
            }
        }
    }

    val ancashCities = listOf(
        "Huaraz" to (-9.5298 to -77.5289),
        "Chimbote" to (-9.0853 to -78.5905),
        "Caraz" to (-9.0483 to -77.8100),
        "Chavín" to (-9.5931 to -77.1772),
        "Casma" to (-9.4750 to -78.3100),
        "Huari" to (-9.3333 to -77.1667)
    )

    LaunchedEffect(Unit) {
        if (weather == null) viewModel.fetchWeather(-9.5298, -77.5289, "Huaraz")
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { 
                searchQuery = it
                viewModel.searchCity(it)
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Buscar ciudad (ej. Chimbote)") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true
        )

        if (searchResults.isNotEmpty() && searchQuery.isNotEmpty()) {
            Card(
                elevation = 4.dp,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Column {
                    searchResults.forEach { result ->
                        Text(
                            text = "${result.name}, ${result.adminArea ?: ""} (${result.country})",
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.fetchWeather(result.latitude, result.longitude, result.name)
                                    searchQuery = ""
                                }
                                .padding(12.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Lugares en Áncash", fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LazyRow(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item {
                    WeatherFilterChip(
                        category = "📍 Mi ubicación",
                        isSelected = cityName == "Mi ubicación",
                        onClick = {
                            locationPermissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        }
                    )
                }
                items(ancashCities) { (name, coords) ->
                    val (lat, lon) = coords
                    WeatherFilterChip(
                        category = name,
                        isSelected = cityName == name,
                        onClick = { viewModel.fetchWeather(lat, lon, name) }
                    )
                }
            }
            IconButton(onClick = onSelectOnMap) {
                Icon(Icons.Default.Map, contentDescription = "Seleccionar en mapa", tint = MaterialTheme.colors.primary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        Text("Clima en $cityName", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        
        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (weather != null) {
            val current = weather.currentWeather
            
            Icon(
                imageVector = if (current.weatherCode < 3) Icons.Default.WbSunny else Icons.Default.Cloud,
                contentDescription = "Estado del clima",
                modifier = Modifier.size(100.dp),
                tint = if (current.weatherCode < 3) Color(0xFFFFD600) else Color.Gray
            )
            
            Text("${current.temperature}°C", fontSize = 48.sp, fontWeight = FontWeight.Light)
            Text(getWeatherDescription(current.weatherCode), fontSize = 20.sp, color = Color.Gray)
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Card(elevation = 2.dp, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Detalles actuales", fontWeight = FontWeight.Bold)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Velocidad del Viento")
                        Text("${current.windspeed} km/h")
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Última actualización")
                        Text("${current.time.takeLast(5)} (UTC-5)")
                    }
                }
            }
        } else {
            Text("No se pudo cargar el clima")
            Button(onClick = { viewModel.fetchWeather(-9.5298, -77.5289, "Huaraz") }) {
                Icon(Icons.Default.Refresh, contentDescription = null)
                Text("Reintentar")
            }
        }
    }
}

@Composable
fun WeatherFilterChip(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
        ),
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(text = category)
    }
}

private fun getWeatherDescription(code: Int): String {
    return when (code) {
        0 -> "Cielo despejado"
        1, 2, 3 -> "Parcialmente nublado"
        45, 48 -> "Neblina"
        51, 53, 55 -> "Llovizna"
        61, 63, 65 -> "Lluvia"
        71, 73, 75 -> "Nieve"
        80, 81, 82 -> "Chubascos"
        95, 96, 99 -> "Tormenta"
        else -> "Desconocido"
    }
}
