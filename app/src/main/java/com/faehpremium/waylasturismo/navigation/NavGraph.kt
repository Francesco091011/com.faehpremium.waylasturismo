package com.faehpremium.waylasturismo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.faehpremium.waylasturismo.ui.*

@Composable
fun setupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("map") { MapScreen() }
        composable("destinations") { DestinationsScreen() }
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
