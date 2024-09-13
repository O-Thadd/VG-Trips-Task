package com.app.vgtask.ui.screens.tripCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.CalendarItem
import com.app.vgtask.data.models.City
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.repos.CitiesRepo
import com.app.vgtask.data.repos.UserRepo
import com.app.vgtask.ui.models.DataStatus
import com.app.vgtask.ui.models.VGTaskData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TripCreationViewModel @Inject constructor(val userRepo: UserRepo, private val citiesRepo: CitiesRepo): ViewModel() {

    private val _uiState = MutableStateFlow(TripCreationUiState.INITIAL)
    val uiState = _uiState.asStateFlow()

    private lateinit var allCities: List<City>

    fun searchCity(searchTerm: String){
        if (searchTerm.isBlank()){
            _uiState.value = _uiState.value.copy(cities = VGTaskData(allCities, DataStatus.DEFAULT))
            return
        }

        val foundCities = allCities.filter { it.name.contains(searchTerm, true) || it.country.contains(searchTerm, true) }
        _uiState.value = _uiState.value.copy(cities = VGTaskData(foundCities, DataStatus.DEFAULT))
    }

    fun setDestination(cityId: String){
        _uiState.value = _uiState.value.copy(destinationCityId = cityId)
    }

    fun setStartDate(start: CalendarItem) {
        _uiState.value = _uiState.value.copy(startDate = start)
    }

    fun setEndDate(end: CalendarItem) {
        _uiState.value = _uiState.value.copy(endDate = end)
    }

    fun completeInfo(name: String, style: String, description: String){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    tripName = name,
                    style = style,
                    description = description,
                    creationStatus = DataStatus.BUSY
                )

                val collectedData = _uiState.value
                val newTrip = Trip(
                    id = UUID.randomUUID().toString(),
                    name = collectedData.tripName,
                    style = collectedData.style,
                    start = collectedData.startDate!!.getTimeStamp(),
                    end = collectedData.endDate!!.getTimeStamp(),
                    destinationId = collectedData.destinationCityId!!,
                    hasFlight = false,
                    hasHotels = false,
                    hasActivities = false
                )
                userRepo.addTrip(newTrip)

                _uiState.value = _uiState.value.copy(
                    creationStatus = DataStatus.PASSED,
                    newlyCreatedTripId = newTrip.id
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(creationStatus = DataStatus.FAILED)
            }
        }
    }

    fun resetCreationStatus(){
        _uiState.value = _uiState.value.copy(creationStatus = DataStatus.DEFAULT)
    }

    fun resetState(){
        _uiState.value = TripCreationUiState.INITIAL
    }

    fun refreshCities(){
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(cities = VGTaskData(null, DataStatus.BUSY))
                val cities = citiesRepo.getCities()
                _uiState.value = _uiState.value.copy(cities = VGTaskData(cities, DataStatus.DEFAULT))
            } catch (e: Exception){
                _uiState.value = _uiState.value.copy(cities = VGTaskData(emptyList(), DataStatus.FAILED))
            }
        }
    }

    init {
        viewModelScope.launch {
            try {
                _uiState.value = TripCreationUiState.INITIAL
                val cities = citiesRepo.getCities()
                allCities = cities
                _uiState.value = _uiState.value.copy(cities = VGTaskData(cities, DataStatus.DEFAULT))
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(cities = VGTaskData(emptyList(), DataStatus.FAILED))
            }
        }
    }
}
