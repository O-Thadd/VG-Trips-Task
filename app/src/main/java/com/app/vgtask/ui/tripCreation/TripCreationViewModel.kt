package com.app.vgtask.ui.tripCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.repos.CitiesRepo
import com.app.vgtask.data.repos.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TripCreationViewModel(userRepo: UserRepo, citiesRepo: CitiesRepo): ViewModel() {

    private val _uiState = MutableStateFlow<TripCreationUiState?>(null)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = TripCreationUiState.getBlank(citiesRepo.getCities())
        }
    }
}