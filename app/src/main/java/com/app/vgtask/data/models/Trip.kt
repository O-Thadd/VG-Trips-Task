package com.app.vgtask.data.models

import com.app.vgtask.getFormattedDate
import com.app.vgtask.ui.TripStyle

data class Trip(
    val id: String,
    val name: String,
    val style: String,
    val start: Long,
    val end: Long,
    val destinationId: String,
    val activities: List<String>,
    val flights: List<String>,
    val hotels: List<String>
)

data class UiTrip(
    val id: String,
    val name: String,
    val startDate: String,
    val endDate: String,
    val ordinalStateDate: String,
    val duration: String,
    val destination: String,
    val imageUrl: String,
    val style: TripStyle,
    val activities: List<String>,
    val flights: List<String>,
    val hotels: List<String>
)

fun Trip.toUiTrip(getCity: (String) -> City): UiTrip{
    val durationInMillis = end - start
    val durationInDays = durationInMillis / (1000 * 60 * 60 * 24)
    val city = getCity(destinationId)

    return UiTrip(
        id = id,
        name = name,
        startDate = getFormattedDate(start),
        endDate = getFormattedDate(end),
        ordinalStateDate = getFormattedDate(start, true),
        duration = "$durationInDays days",
        destination = "${city.name}, ${city.country}",
        imageUrl = city.image,
        style = TripStyle.getStyle(style)!!,
        activities = emptyList(),
        flights = emptyList(),
        hotels = emptyList()
    )
}