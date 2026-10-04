package com.faehpremium.waylasturismo.shop

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ShopItem(
    val id: String,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val isDigital: Boolean = true
)

data class PurchaseReceipt(
    val transactionId: String,
    val itemId: String,
    val itemTitle: String,
    val amountPaid: Double,
    val timestamp: Long = System.currentTimeMillis()
)

class ShopManager {
    private val _catalog = listOf(
        ShopItem("shop_1", "Guía Completa de Trekking Cordillera Blanca (PDF)", 29.99, "Digital", "Guía oficial detallada con altimetrías, mapas de rutas y consejos de aclimatación."),
        ShopItem("shop_2", "Pack de Mapas Offline GPS Ancash Pro", 49.99, "Software", "Mapas topográficos descargables de alta precisión para uso sin conexión a internet."),
        ShopItem("shop_3", "Audio-Guía Interactiva Chavín de Huántar", 19.99, "Multimedia", "Narración histórica inmersiva guiada por expertos arqueólogos."),
        ShopItem("shop_4", "Manuscrito y Recetario Gastronómico Ancashino", 15.00, "Digital", "Recetas tradicionales de Pachamanca, Cuchicanca y Chocho con historia local.")
    )

    val catalog: List<ShopItem> get() = _catalog

    private val _purchasedItems = MutableStateFlow<Set<String>>(emptySet())
    val purchasedItems: StateFlow<Set<String>> = _purchasedItems.asStateFlow()

    private val _purchaseHistory = MutableStateFlow<List<PurchaseReceipt>>(emptyList())
    val purchaseHistory: StateFlow<List<PurchaseReceipt>> = _purchaseHistory.asStateFlow()

    fun purchaseItem(item: ShopItem): PurchaseReceipt {
        val transactionId = "TXN-${System.currentTimeMillis()}"
        val receipt = PurchaseReceipt(
            transactionId = transactionId,
            itemId = item.id,
            itemTitle = item.title,
            amountPaid = item.price
        )
        
        val currentPurchased = _purchasedItems.value.toMutableSet()
        currentPurchased.add(item.id)
        _purchasedItems.value = currentPurchased

        val currentHistory = _purchaseHistory.value.toMutableList()
        currentHistory.add(receipt)
        _purchaseHistory.value = currentHistory

        return receipt
    }

    fun isItemPurchased(itemId: String): Boolean {
        return _purchasedItems.value.contains(itemId)
    }
}
