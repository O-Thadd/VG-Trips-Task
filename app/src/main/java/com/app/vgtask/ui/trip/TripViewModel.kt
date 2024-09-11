package com.app.vgtask.ui.trip

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.repos.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val TRIP_ID_KEY = "tripId_key"

class TripViewModel(userRepo: UserRepo, savedStateHandle: SavedStateHandle): ViewModel() {
    private val _trip = MutableStateFlow<Trip?>(null)
    val trip = _trip.asStateFlow()

    init {
        viewModelScope.launch {
            val tripId: String = checkNotNull(savedStateHandle[TRIP_ID_KEY])
            _trip.value = userRepo.getUser().trips.find { it.id == tripId }
        }
    }
}