package com.app.vgtask.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepo: UserRepo, private val citiesRepo: CitiesRepo): ViewModel() {

    private val _uiState = MutableStateFlow<VGTaskData<List<UiTrip>>>(VGTaskData(emptyList(), DataStatus.DEFAULT))
    val trips = _uiState.asStateFlow()

    suspend fun refresh() {
        try {
            val gottenTrips = userRepo.getUser().trips?.map { trip ->
                trip.toUiTrip(viewModelScope) { cityId ->
                    citiesRepo.getCities().find { cityId == it.id }!!
                }
            } ?: emptyList()

            _uiState.value = VGTaskData(gottenTrips, DataStatus.DEFAULT)
        } catch (e: Exception) {
            _uiState.value = VGTaskData(null, DataStatus.FAILED)
        }
    }

    fun resetRefreshStatus(){
        _uiState.value = _uiState.value.copy(status = DataStatus.DEFAULT)
    }

    init {
        viewModelScope.launch {
            refresh()
        }
    }
}