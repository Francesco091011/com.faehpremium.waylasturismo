package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class TravelDate(val month: String, val recommendation: String, val events: String)

@Composable
fun CalendarScreen() {
    val dates = listOf(
        TravelDate("Enero", "Aniversario de Huaraz.", "Actividades protocolares y ferias."),
        TravelDate("Febrero", "Carnavales Huaracinos.", "Entierro del Ño Carnavalón y cruces."),
        TravelDate("Marzo", "Semana Santa en Huaraz.", "Procesiones solemnes y alfombras."),
        TravelDate("Abril", "Temporada de cosecha.", "Festividades en los pueblos del Callejón."),
        TravelDate("Mayo", "Festividad del Señor de la Soledad.", "Patrón de Huaraz, danzas típicas."),
        TravelDate("Junio", "Semana del Andinismo.", "Competencias de montaña y escalada."),
        TravelDate("Julio", "Fiestas Patrias.", "Aniversario de la creación política de Huaraz."),
        TravelDate("Agosto", "Virgen de la Asunción.", "Festividad tradicional en Chacas."),
        TravelDate("Septiembre", "Virgen de las Mercedes.", "Gran fiesta patronal en Carhuaz."),
        TravelDate("Octubre", "Temporada de lluvia moderada.", "Paisajes verdes e intensos."),
        TravelDate("Noviembre", "Día de los Difuntos.", "Tradiciones ancestrales y tantawawas."),
        TravelDate("Diciembre", "Navidad Huaylina.", "Nacimientos y cánticos regionales.")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = "Calendario de Viaje",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        items(dates) { date ->
            CalendarItem(date)
        }
    }
}

@Composable
fun CalendarItem(date: TravelDate) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = date.month, style = MaterialTheme.typography.h6, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "💡 ${date.recommendation}", style = MaterialTheme.typography.body1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "🎉 ${date.events}", style = MaterialTheme.typography.body2)
        }
    }
}
