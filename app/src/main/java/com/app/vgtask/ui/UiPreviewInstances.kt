package com.app.vgtask.ui

import com.app.vgtask.data.models.City
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.TripStyle
import com.app.vgtask.ui.models.VGTaskData
import com.app.vgtask.ui.screens.tripCreation.TripCreationUiState

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
    hasActivities = false,
    hasFlights = false,
    hasHotels = false
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
    hasActivities = false,
    hasFlights = false,
    hasHotels = false
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
    hasActivities = false,
    hasFlights = false,
    hasHotels = false
)

val testPreviewTrips = listOf(trip1, trip2, trip3)

val city1 = City(
    id = "city1",
    name = "Tokyo",
    country =  "Japan",
    flag = "https://flagcdn.com/w320/jp.png",
    countryCode = "JP",
    image = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Ftokyo.jpg?alt=media&token=c43746c8-5a82-4cf5-b26b-1ce892f262a2"
)

val city2 = City(
    id = "city2",
    name = "Delhi",
    country =  "India",
    flag = "https://flagcdn.com/w320/in.png",
    countryCode = "IN",
    image = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Fdelhi.png?alt=media&token=f6b9f84d-9d22-4003-b163-4998ca220f47"
)

val city3 = City(
    id = "city3",
    name = "Paris",
    country =  "France",
    flag = "https://flagcdn.com/w320/fr.png",
    countryCode = "FR",
    image = "https://firebasestorage.googleapis.com/v0/b/thadd-dev-realm.appspot.com/o/cities-pics%2Fparis.jpg?alt=media&token=8e816bcb-e82f-478b-9ecd-c37092d0bb90"
)

val testPreviewCities = listOf(city1, city2, city3)

val previewTestTripCreationUiState = TripCreationUiState(
    VGTaskData(data = testPreviewCities, status = DataStatus.DEFAULT),
    startDate = null,
    endDate = null,
    tripName = "",
    style = "",
    description = "",
    destinationCityId = null,
    creationStatus = DataStatus.DEFAULT,
    newlyCreatedTripId = null
)
