package com.app.vgtask.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.vgtask.data.models.City
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.repos.UserRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(userRepo: UserRepo): ViewModel() {

    private val _uiState = MutableStateFlow<List<Trip>>(emptyList())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _uiState.value = userRepo.getUser().trips
        }
    }
}