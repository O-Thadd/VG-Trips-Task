package com.app.vgtask.ui.screens.trip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.data.models.User
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
    private val userRepo: UserRepo,
    citiesRepo: CitiesRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _trip = MutableStateFlow<VGTaskData<UiTrip?>>(VGTaskData(null, DataStatus.BUSY))
    val trip = _trip.asStateFlow()

    fun addItinerary(
        addHotel: Boolean = false,
        addFlight: Boolean = false,
        addActivity: Boolean = false
    ){
        if (addHotel){
            addHotel()
        }

        if (addFlight){
            addFlight()
        }

        if (addActivity){
            addActivity()
        }
    }
    private fun addHotel(){
        viewModelScope.launch {
            try {
                val (thisUiTrip, user, trips) = getNecessaryDataForItineraryUpdate()
                val updatedTrip = trips.find { it.id == thisUiTrip.id }!!.copy(hasHotels = true)
                updateTrip(trips, updatedTrip, user)

                val updateUiTrip = thisUiTrip.copy(hasHotels = true)
                _trip.value = VGTaskData(updateUiTrip, DataStatus.DEFAULT)
            } catch (e: Exception){
//                throw e
                _trip.value = VGTaskData(null, DataStatus.FAILED)
            }
        }
    }

    private fun addFlight(){
        viewModelScope.launch {
            try {
                val (thisUiTrip, user, trips) = getNecessaryDataForItineraryUpdate()
                val updatedTrip = trips.find { it.id == thisUiTrip.id }!!.copy(hasFlight = true)
                updateTrip(trips, updatedTrip, user)

                val updateUiTrip = thisUiTrip.copy(hasFlights = true)
                _trip.value = VGTaskData(updateUiTrip, DataStatus.DEFAULT)
            } catch (e: Exception){
                _trip.value = VGTaskData(null, DataStatus.FAILED)
            }
        }
    }

    private fun addActivity(){
        viewModelScope.launch {
            try {
                val (thisUiTrip, user, trips) = getNecessaryDataForItineraryUpdate()
                val updatedTrip = trips.find { it.id == thisUiTrip.id }!!.copy(hasActivities = true)
                updateTrip(trips, updatedTrip, user)

                val updateUiTrip = thisUiTrip.copy(hasActivities = true)
                _trip.value = VGTaskData(updateUiTrip, DataStatus.DEFAULT)
            } catch (e: Exception){
                _trip.value = VGTaskData(null, DataStatus.FAILED)
            }
        }
    }

    fun resetStatus(){
        _trip.value = _trip.value.copy(status = DataStatus.DEFAULT)
    }

    private suspend fun updateTrip(
        trips: MutableList<Trip>,
        updatedTrip: Trip,
        user: User
    ) {
        trips.removeIf { it.id == updatedTrip.id }
        trips.add(updatedTrip)
        val updatedUser = user.copy(trips = trips)
        userRepo.updateUser(updatedUser)
    }

    private suspend fun getNecessaryDataForItineraryUpdate(): Triple<UiTrip, User, MutableList<Trip>> {
        _trip.value = _trip.value.copy(status = DataStatus.BUSY)
        val thisUiTrip = _trip.value.data!!
        val user = userRepo.getUser()
        val trips = user.trips!!.toMutableList()
        return Triple(thisUiTrip, user, trips)
    }

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