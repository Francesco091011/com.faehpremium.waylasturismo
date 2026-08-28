package com.faehpremium.waylasturismo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.faehpremium.waylasturismo.ui.*

@Composable
fun setupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable(
            route = "map?lat={lat}&lon={lon}&label={label}",
            arguments = listOf(
                navArgument("lat") { type = NavType.StringType; defaultValue = "-9.5298" },
                navArgument("lon") { type = NavType.StringType; defaultValue = "-77.5289" },
                navArgument("label") { type = NavType.StringType; defaultValue = "Huaraz, Áncash" }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull() ?: -9.5298
            val lon = backStackEntry.arguments?.getString("lon")?.toDoubleOrNull() ?: -77.5289
            val label = backStackEntry.arguments?.getString("label") ?: "Huaraz, Áncash"
            MapScreen(latitude = lat, longitude = lon, label = label)
        }
        composable("destinations") { 
            DestinationsScreen(onNavigateToMap = { lat, lon, label ->
                navController.navigate("map?lat=$lat&lon=$lon&label=$label")
            }) 
        }
        composable("accommodations") { AccommodationsScreen() }
        composable("restaurants") { RestaurantsScreen() }
        composable("routes") { RoutesScreen() }
        composable("tours") { ToursScreen() }
        composable("weather") { WeatherScreen() }
        composable("calendar") { CalendarScreen() }
        composable("tips") { TipsScreen() }
        composable("gastronomy") { GastronomyScreen() }
        composable("archaeology") { ArchaeologyScreen() }
        composable("adventure_sports") { AdventureSportsScreen() }
        composable("exchange_rate") { ExchangeRateScreen() }
    }
}
