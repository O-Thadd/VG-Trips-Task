package com.app.vgtask.data.repos

import com.app.vgtask.data.dataSources.localStore.VGTaskDataStore
import com.app.vgtask.data.dataSources.remote.VGTaskRemoteService
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.models.User

class UserRepo(private val localStore: VGTaskDataStore, private val remoteService: VGTaskRemoteService) {

    suspend fun getUser(): User {
        val userId = localStore.getUserId()

        return if (userId != null){
            remoteService.getUser(userId);
        } else {
            val user = remoteService.createUser()
            localStore.updateUserId(user.id!!)
            user
        }
    }

    suspend fun addTrip(trip: Trip){
        val userId = localStore.getUserId()!!
        val user = remoteService.getUser(userId)
        val newTrips = user.trips.toMutableList().apply { add(trip) }
        remoteService.updateUser(userId, user.copy(trips = newTrips))
    }
}