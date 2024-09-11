package com.app.vgtask.data.repos

import com.app.vgtask.data.dataSources.remote.VGTaskRemoteService
import com.app.vgtask.data.models.City

class CitiesRepo(private val remoteService: VGTaskRemoteService) {
    fun getCities(): List<City> {
        return remoteService.getCities()
    }
}