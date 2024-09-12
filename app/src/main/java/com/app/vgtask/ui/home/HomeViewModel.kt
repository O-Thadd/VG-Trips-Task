package com.app.vgtask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.models.UiTrip
import com.app.vgtask.data.models.toUiTrip
import com.app.vgtask.data.repos.CitiesRepo
import com.app.vgtask.data.repos.UserRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(userRepo: UserRepo, citiesRepo: CitiesRepo): ViewModel() {

    private val _uiState = MutableStateFlow<List<UiTrip>>(emptyList())
    val trips = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = userRepo.getUser().trips?.map { trip ->
                trip.toUiTrip(viewModelScope) { cityId ->
                    citiesRepo.getCities().find { cityId == it.id }!!
                }
            } ?: emptyList()
        }
    }
}