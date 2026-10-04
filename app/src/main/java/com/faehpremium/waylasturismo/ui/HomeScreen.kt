package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class MenuOption(val title: String, val route: String, val icon: ImageVector)

@Composable
fun HomeScreen(navController: NavHostController) {
    val options = listOf(
        MenuOption("Destinos", "destinations", Icons.Default.Place),
        MenuOption("Mapa", "map", Icons.Default.Map),
        MenuOption("Alojamientos", "accommodations", Icons.Default.Hotel),
        MenuOption("Restaurantes", "restaurants", Icons.Default.Restaurant),
        MenuOption("Rutas", "routes", Icons.Default.Route),
        MenuOption("Tours", "tours", Icons.Default.Tour),
        MenuOption("Clima", "weather", Icons.Default.Cloud),
        MenuOption("Calendario", "calendar", Icons.Default.CalendarMonth),
        MenuOption("Gastronomía", "gastronomy", Icons.Default.Fastfood),
        MenuOption("Arqueología", "archaeology", Icons.Default.AccountBalance),
        MenuOption("Aventura", "adventure_sports", Icons.Default.Hiking),
        MenuOption("Tipo de Cambio", "exchange_rate", Icons.Default.CurrencyExchange),
        MenuOption("Historia", "history", Icons.Default.HistoryEdu),
        MenuOption("Tips", "tips", Icons.Default.Info),
        MenuOption("Tienda Pro", "shop", Icons.Default.ShoppingBag),
        MenuOption("Marketplace", "marketplace", Icons.Default.Handshake),
        MenuOption("Membresía VIP", "premium", Icons.Default.WorkspacePremium),
        MenuOption("Acerca de", "about", Icons.Default.Help)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Waylas Turismo",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(options) { option ->
                MenuCard(option) {
                    navController.navigate(option.route)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuCard(option: MenuOption, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(110.dp),
        elevation = 6.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = option.icon, 
                contentDescription = option.title, 
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = option.title, 
                fontSize = 14.sp, 
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}
