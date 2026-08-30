package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faehpremium.waylasturismo.ui.viewmodel.CurrencyViewModel
import java.util.Currency
import java.util.Locale

@Composable
fun ExchangeRateScreen(viewModel: CurrencyViewModel = viewModel()) {
    val currencyData = viewModel.currencyState.value
    val isLoading = viewModel.isLoading.value
    val error = viewModel.error.value

    var amount by remember { mutableStateOf("1") }
    var baseCurrency by remember { mutableStateOf("USD") }
    var targetCurrency by remember { mutableStateOf("PEN") }
    
    val allCurrencies = currencyData?.rates?.keys?.toList()?.sorted() ?: listOf("USD", "PEN", "EUR")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calculadora de Divisas", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        if (isLoading) {
            CircularProgressIndicator()
        } else if (error != null) {
            Text(error, color = MaterialTheme.colors.error)
            Button(onClick = { viewModel.fetchRates(baseCurrency) }) {
                Text("Reintentar")
            }
        } else {
            Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Monto a convertir
                    OutlinedTextField(
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("Monto") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    // De Moneda
                    CurrencySelector(
                        label = "De:",
                        selected = baseCurrency,
                        options = allCurrencies,
                        onSelected = { 
                            baseCurrency = it
                            viewModel.fetchRates(it)
                        }
                    )

                    IconButton(
                        onClick = {
                            val temp = baseCurrency
                            baseCurrency = targetCurrency
                            targetCurrency = temp
                            viewModel.fetchRates(baseCurrency)
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Icon(Icons.Default.SwapVert, contentDescription = "Intercambiar")
                    }

                    // A Moneda
                    CurrencySelector(
                        label = "A:",
                        selected = targetCurrency,
                        options = allCurrencies,
                        onSelected = { targetCurrency = it }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(24.dp))

                    // Resultado
                    val rate = currencyData?.rates?.get(targetCurrency) ?: 0.0
                    val result = (amount.toDoubleOrNull() ?: 0.0) * rate

                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                        Text("Resultado:", style = MaterialTheme.typography.caption)
                        Text(
                            text = "${String.format("%.2f", result)} $targetCurrency",
                            style = MaterialTheme.typography.h4,
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Tasa: 1 $baseCurrency = $rate $targetCurrency",
                            style = MaterialTheme.typography.body2
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Última actualización: ${currencyData?.lastUpdate ?: "N/A"}",
                style = MaterialTheme.typography.caption
            )
        }
    }
}

private fun getCurrencyDisplayName(code: String): String {
    return try {
        val currency = java.util.Currency.getInstance(code)
        "$code - ${currency.getDisplayName(java.util.Locale("es"))}"
    } catch (e: Exception) {
        code
    }
}

@Composable
fun CurrencySelector(
    label: String,
    selected: String,
    options: List<String>,
    onSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Text(label, modifier = Modifier.width(40.dp), fontWeight = FontWeight.Bold)
        Box(
            modifier = Modifier
                .weight(1f)
                .clickable { expanded = true }
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(getCurrencyDisplayName(selected), modifier = Modifier.weight(1f))
                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth(0.8f).heightIn(max = 400.dp)
            ) {
                options.forEach { option ->
                    DropdownMenuItem(onClick = {
                        onSelected(option)
                        expanded = false
                    }) {
                        Text(getCurrencyDisplayName(option))
                    }
                }
            }
        }
    }
}
