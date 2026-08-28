package com.faehpremium.waylasturismo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.faehpremium.waylasturismo.model.Restaurant

@Composable
fun RestaurantsScreen() {
    val allRestaurants = listOf(
        Restaurant("1", "El Tarwi", "Comida Típica", 4.8, "Especialistas en Picante de Cuy y Llunca Cashki."),
        Restaurant("2", "Encuentro", "Fusión", 4.7, "Lo mejor de la cocina regional con un toque moderno."),
        Restaurant("3", "Manjar Blanco", "Dulces y Postres", 4.9, "Famosos por el manjarblanco y dulces de Caraz."),
        Restaurant("4", "Cebichería El Colorado", "Pescados y Mariscos", 4.6, "Los mejores pescados de la costa de Chimbote."),
        Restaurant("5", "Manka", "Tradicional", 4.7, "Sabor auténtico huaracino en un ambiente acogedor.")
    )

    val categories = listOf("Todos", "Comida Típica", "Fusión", "Dulces y Postres", "Pescados y Mariscos", "Tradicional")
    var selectedCategory by remember { mutableStateOf("Todos") }

    val filteredRestaurants = if (selectedCategory == "Todos") {
        allRestaurants
    } else {
        allRestaurants.filter { it.category == selectedCategory }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Restaurantes y Gastronomía",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                FilterChip(
                    category = category,
                    isSelected = category == selectedCategory,
                    onClick = { selectedCategory = category }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(filteredRestaurants) { restaurant ->
                RestaurantItem(restaurant)
            }
        }
    }
}

@Composable
fun FilterChip(category: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            contentColor = if (isSelected) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = category)
    }
}

@Composable
fun RestaurantItem(restaurant: Restaurant) {
    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = restaurant.name, style = MaterialTheme.typography.h6)
                Text(text = "⭐ ${restaurant.rating}", style = MaterialTheme.typography.subtitle1)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = restaurant.category, style = MaterialTheme.typography.subtitle2, color = MaterialTheme.colors.primary)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = restaurant.description, style = MaterialTheme.typography.body2)
        }
    }
}
