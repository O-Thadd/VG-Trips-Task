package com.app.vgtask.ui.screens.tripCreation

import com.app.vgtask.CalendarItem
import com.app.vgtask.data.models.City
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.VGTaskData

data class TripCreationUiState(
    val cities: VGTaskData<List<City>>,
    val startDate: CalendarItem?,
    val endDate: CalendarItem?,
    val tripName: String,
    val style: String,
    val description: String,
    val destinationCityId: String?,
    val creationStatus: DataStatus,
    val newlyCreatedTripId: String?
) {
    companion object {
        val INITIAL = TripCreationUiState(
            VGTaskData(null, DataStatus.BUSY),
            null,
            null,
            "",
            "",
            "",
            destinationCityId = null,
            creationStatus = DataStatus.DEFAULT,
            newlyCreatedTripId = null
        )
    }
}
