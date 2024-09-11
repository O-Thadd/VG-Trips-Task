package com.app.vgtask.ui.tripCreation

import androidx.lifecycle.ViewModel
import com.app.vgtask.data.repos.CitiesRepo
import com.app.vgtask.data.repos.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TripCreationViewModel(userRepo: UserRepo, citiesRepo: CitiesRepo): ViewModel() {

    private val _uiState = MutableStateFlow<TripCreationUiState?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.value = TripCreationUiState.getBlank(citiesRepo.getCities())
    }
}