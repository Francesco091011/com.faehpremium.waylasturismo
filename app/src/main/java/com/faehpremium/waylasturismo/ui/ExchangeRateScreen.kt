package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import org.json.JSONObject

@Composable
fun ExchangeRateScreen() {
    var penRate by remember { mutableStateOf("-") }
    var eurRate by remember { mutableStateOf("-") }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        loading = true
        try {
            val result = withContext(Dispatchers.IO) {
                // Usando una API de ejemplo (puede requerir API Key en producción)
                val response = URL("https://api.exchangerate-api.com/v4/latest/USD").readText()
                JSONObject(response)
            }
            val rates = result.getJSONObject("rates")
            penRate = rates.optDouble("PEN", 0.0).toString()
            eurRate = rates.optDouble("EUR", 0.0).toString()
        } catch (e: Exception) {
            penRate = "Error"
            eurRate = "Error"
        }
        loading = false
    }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Conversión de Moneda (USD Base)", style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.height(24.dp))
                Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("1 Dólar (USD) = $penRate Soles (PEN)", style = MaterialTheme.typography.h6)
                        Divider(modifier = Modifier.padding(vertical = 8.dp))
                        Text("1 Dólar (USD) = $eurRate Euros (EUR)", style = MaterialTheme.typography.h6)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Nota: Los valores son referenciales.", style = MaterialTheme.typography.caption)
            }
        }
    }
}
