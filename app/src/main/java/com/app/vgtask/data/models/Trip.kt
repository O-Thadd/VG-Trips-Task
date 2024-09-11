package com.app.vgtask.data.models

data class Trip(
    val id: String,
    val name: String,
    val type: String,
    val imageUrl: String,
    val start: Long,
    val end: Long,
    val destinationId: String,
    val activities: List<String>,
    val flights: List<String>,
    val hotels: List<String>
)