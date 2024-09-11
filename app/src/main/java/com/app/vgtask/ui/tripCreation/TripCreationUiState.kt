package com.app.vgtask.ui.tripCreation

import com.app.vgtask.data.models.City

data class TripCreationUiState(
    val cities: List<City>,
    var startDate: Triple<Int, Int, Int>,
    var endDate: Triple<Int, Int, Int>,
    var tripName: String,
    var style: String,
    var description: String
) {
    companion object {
        fun getBlank(cities: List<City>): TripCreationUiState {
            return TripCreationUiState(
                cities,
                Triple(0, 0, 0),
                Triple(0, 0, 0),
                "",
                "",
                ""
            )
        }
    }
}