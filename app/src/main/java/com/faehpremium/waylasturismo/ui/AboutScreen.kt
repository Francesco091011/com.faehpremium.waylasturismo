package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logotipo (Usando un icono por ahora como placeholder)
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = "Logo Waylas Turismo",
            modifier = Modifier.size(100.dp),
            tint = MaterialTheme.colors.primary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Waylas Turismo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        
        Text(
            text = "Guía turística de Áncash",
            fontSize = 16.sp,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(text = "Versión: 0.1.7", fontWeight = FontWeight.Medium) // versionText as requested
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "Sitio Web:", fontWeight = FontWeight.Bold)
        Text(
            text = "www.waylasturismo.pe", // Placeholder
            color = MaterialTheme.colors.secondary
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "Repositorio de GitHub:", fontWeight = FontWeight.Bold)
        Text(
            text = "https://github.com/francesco091011/com.faehpremium.waylasturismo",
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = MaterialTheme.colors.secondary
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Text(
            text = "© 2026 Waylas Turismo\nDesarrollado con ❤️ para Áncash",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}
