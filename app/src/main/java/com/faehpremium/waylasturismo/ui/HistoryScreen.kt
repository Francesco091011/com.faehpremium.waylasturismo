package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HistoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Historia Ancashina",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "Áncash es cuna de una de las culturas más antiguas de los Andes: Chavín. " +
                   "Desde tiempos inmemoriales, este departamento ha sido un punto neurálgico para el " +
                   "desarrollo de la civilización andina.",
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        Text("Época Preincaica", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            "La Cultura Chavín (1200 a.C. - 200 a.C.) floreció en el Callejón de Conchucos, " +
            "dejando como legado el majestuoso Templo de Chavín de Huántar. Posteriormente, " +
            "culturas como Recuay y Huari también dejaron su huella en el territorio.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Época Colonial y Republicana", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            "Durante la colonia, Áncash fue importante por su minería. En la era republicana, " +
            "el departamento fue escenario de importantes eventos, incluyendo la Batalla de Yungay " +
            "en 1839, que consolidó la independencia del Perú frente a la Confederación Perú-Boliviana.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Siglo XX: El Terremoto de 1970", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Text(
            "Un evento que marcó la historia moderna de Áncash fue el devastador terremoto y aluvión " +
            "de 1970, que sepultó la ciudad de Yungay y transformó la fisonomía de todo el Callejón de Huaylas.",
            fontSize = 16.sp
        )
    }
}
