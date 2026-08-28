package com.faehpremium.waylasturismo.model

data class ArchaeologicalSite(
    val id: String,
    val name: String,
    val description: String,
    val location: String,
    val imageUrl: String? = null
)
