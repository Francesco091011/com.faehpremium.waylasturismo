package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.faehpremium.waylasturismo.premium.PremiumManager
import com.faehpremium.waylasturismo.premium.SubscriptionPlan

@Composable
fun PremiumScreen() {
    val premiumManager = remember { PremiumManager() }
    val isPremium by premiumManager.isPremium.collectAsState()
    val activePlan by premiumManager.activePlan.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.WorkspacePremium, contentDescription = "VIP", tint = Color(0xFFFFB300), modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text("Waylas Pro & VIP", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary)
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        Text("Desbloquea mapas offline ilimitados, asistencia 24/7 y contenido exclusivo.", fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(16.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = if (isPremium) Color(0xFFFFF8E1) else Color(0xFFF5F5F5),
            elevation = 2.dp,
            shape = MaterialTheme.shapes.medium
        ) {
            Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFB300), modifier = Modifier.size(32.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (isPremium) "¡Eres Miembro ${activePlan?.title ?: "Pro"}!" else "Estado: Cuenta Gratuita",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = if (isPremium) Color(0xFF8D6E63) else Color.DarkGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = if (isPremium) "Disfrutas de acceso ilimitado a todas las funciones." else "Actualiza a Pro para potenciar tu experiencia en Áncash.",
                    fontSize = 13.sp,
                    color = Color.Gray
                )
                if (isPremium) {
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedButton(onClick = { premiumManager.cancelSubscription() }) {
                        Text("Cancelar Suscripción", color = Color.Red)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("Planes de Suscripción", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(SubscriptionPlan.values().toList()) { plan ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = 4.dp
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(plan.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text(plan.price, fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary, fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Button(
                            onClick = { premiumManager.subscribe(plan) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFB300))
                        ) {
                            Text("Elegir ${plan.title}", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}
