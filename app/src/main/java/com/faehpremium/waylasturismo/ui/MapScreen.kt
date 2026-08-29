package com.faehpremium.waylasturismo.ui

import android.view.MotionEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker

@Composable
fun MapScreen(
    latitude: Double = -9.5298, // Huaraz por defecto
    longitude: Double = -77.5289,
    zoom: Double = 15.0,
    label: String = "Huaraz, Áncash",
    onLocationSelected: ((Double, Double) -> Unit)? = null
) {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
        }
    }

    DisposableEffect(mapView) {
        onDispose {
            mapView.onDetach()
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            val mapController = view.controller
            mapController.setZoom(zoom)
            val startPoint = GeoPoint(latitude, longitude)
            mapController.setCenter(startPoint)

            view.overlays.clear()

            // Agregar marcador
            val marker = Marker(view)
            marker.position = startPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = label
            view.overlays.add(marker)

            // Evento de clic en el mapa
            if (onLocationSelected != null) {
                val eventsReceiver = object : MapEventsReceiver {
                    override fun singleTapConfirmedHelper(p: GeoPoint): Boolean {
                        onLocationSelected.invoke(p.latitude, p.longitude)
                        return true
                    }
                    override fun longPressHelper(p: GeoPoint): Boolean = false
                }
                view.overlays.add(MapEventsOverlay(eventsReceiver))
            }
        }
    )
}
