package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faehpremium.waylasturismo.shop.ShopManager

@Composable
fun ShopScreen() {
    val shopManager = remember { ShopManager() }
    val purchasedItems by shopManager.purchasedItems.collectAsState()
    var successMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.ShoppingBag, contentDescription = "Tienda", tint = MaterialTheme.colors.primary, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Tienda Pro Waylas", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        Text("Adquiere guías oficiales, mapas offline y contenido exclusivo para tu aventura.", fontSize = 14.sp, color = Color.Gray)
        
        if (successMessage != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                color = Color(0xFFE8F5E9),
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CheckCircle, contentDescription = null, tint = Color(0xFF388E3C))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(successMessage ?: "", color = Color(0xFF2E7D32), fontWeight = FontWeight.Medium)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Catálogo de Productos", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(shopManager.catalog) { item ->
                val isPurchased = purchasedItems.contains(item.id)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(item.title, fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.weight(1f))
                            Text("$${item.price}", fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(item.description, fontSize = 14.sp, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("Categoría: ${item.category}", fontSize = 12.sp, color = Color.Gray)
                            Button(
                                onClick = {
                                    val receipt = shopManager.purchaseItem(item)
                                    successMessage = "¡Compra exitosa! ${receipt.itemTitle} (${receipt.transactionId})"
                                },
                                enabled = !isPurchased
                            ) {
                                Text(if (isPurchased) "Adquirido ✓" else "Comprar Ahora")
                            }
                        }
                    }
                }
            }
        }
    }
}
