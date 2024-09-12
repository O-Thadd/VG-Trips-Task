package com.app.vgtask.data.repos

import com.app.vgtask.data.dataSources.remote.CitiesRemoteService
import com.app.vgtask.data.models.City
import javax.inject.Inject

class CitiesRepo @Inject constructor(private val remoteService: CitiesRemoteService) {
    suspend fun getCities(): List<City> {
        return remoteService.getCities()
    }
}