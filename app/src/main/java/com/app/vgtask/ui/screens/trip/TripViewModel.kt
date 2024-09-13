package com.app.vgtask.ui.screens.trip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.data.models.toUiTrip
import com.app.vgtask.data.repos.CitiesRepo
import com.app.vgtask.data.repos.UserRepo
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.VGTaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val TRIP_ID_KEY = "tripId_key"

@HiltViewModel
class TripViewModel @Inject constructor(
    userRepo: UserRepo,
    citiesRepo: CitiesRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _trip = MutableStateFlow<VGTaskData<UiTrip?>>(VGTaskData(null, DataStatus.BUSY))
    val trip = _trip.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val tripId: String = checkNotNull(savedStateHandle[TRIP_ID_KEY])
                val trip = userRepo.getUser().trips!!.find { it.id == tripId }!!
                val uiTrip = trip.toUiTrip(viewModelScope) { cityId ->
                    citiesRepo.getCities().find { it.id == cityId }!!
                }
                _trip.value = VGTaskData(uiTrip, DataStatus.DEFAULT)
            } catch (e: Exception) {
                _trip.value = VGTaskData(null, DataStatus.FAILED)
            }
        }
    }
}