package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AboutScreen() {
    val uriHandler = LocalUriHandler.current

    SelectionContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logotipo
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
            
            Text(text = "Versión: 0.2.0.1", fontWeight = FontWeight.Medium)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = "Sitio Web:", fontWeight = FontWeight.Bold)
            Text(
                text = "www.waylasturismo.pe",
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://www.waylasturismo.pe")
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(text = "Repositorio de GitHub:", fontWeight = FontWeight.Bold)
            Text(
                text = "https://github.com/francesco091011/com.faehpremium.waylasturismo",
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = MaterialTheme.colors.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    uriHandler.openUri("https://github.com/francesco091011/com.faehpremium.waylasturismo")
                }
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
}
