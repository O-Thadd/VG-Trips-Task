package com.app.vgtask.ui

import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.ui.home.HomeUiState

val trip1 = UiTrip(
    id = "trip1",
    name = "London Chill",
    style = TripStyle.COUPLE,
    destination = "London, UK",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Flondon.jpg?alt=media&token=84f49a3f-a716-463c-acb6-7709c4a6644e",
    startDate = "11 September 2024",
    endDate = "18 September 2024",
    duration = "7 days",
    ordinalStartDate = "11th September 2024",
    activities = emptyList(),
    flights = emptyList(),
    hotels = emptyList()
)

val trip2 = UiTrip(
    id = "trip2",
    name = "Barca Getaway!",
    style = TripStyle.GROUP,
    destination = "Barcelona, Spain",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Fbarcelona.jpg?alt=media&token=0b591442-bea7-43f9-854f-20bf2008cde8",
    startDate = "30 September 2024",
    endDate = "2 October 2024",
    duration = "2 days",
    ordinalStartDate = "30th September 2024",
    activities = emptyList(),
    flights = emptyList(),
    hotels = emptyList()
)

val trip3 = UiTrip(
    id = "trip3",
    name = "Home Sweet Home",
    style = TripStyle.SOLO,
    destination = "Lagos, Nigeria",
    imageUrl = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Flagos.jpg?alt=media&token=95ffec89-d1ad-418d-a185-0ba912468bc6",
    startDate = "1 October 2024",
    endDate = "20 October 2024",
    duration = "19 days",
    ordinalStartDate = "1st October 2024",
    activities = emptyList(),
    flights = emptyList(),
    hotels = emptyList()
)

val testPreviewHomeUiState = HomeUiState(listOf(trip1, trip2, trip3))