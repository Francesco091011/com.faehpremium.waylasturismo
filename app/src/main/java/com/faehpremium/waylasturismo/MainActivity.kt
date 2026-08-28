package com.faehpremium.waylasturismo

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.osmdroid.config.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.faehpremium.waylasturismo.navigation.setupNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Configuración para OpenStreetMap (OsmDroid)
        Configuration.getInstance().load(
            applicationContext,
            PreferenceManager.getDefaultSharedPreferences(applicationContext)
        )
        Configuration.getInstance().userAgentValue = packageName
        
        setContent {
            WaylasTurismoApp()
        }
    }
}

@Composable
fun WaylasTurismoApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Waylas Turismo") },
                    navigationIcon = if (currentRoute != "home") {
                        {
                            IconButton(onClick = { navController.navigateUp() }) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                            }
                        }
                    } else null
                )
            }
        ) { innerPadding ->
            Surface(modifier = Modifier.padding(innerPadding)) {
                setupNavGraph(navController)
            }
        }
    }
}
