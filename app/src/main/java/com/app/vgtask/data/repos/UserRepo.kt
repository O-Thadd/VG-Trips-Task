package com.app.vgtask.data.repos

import com.app.vgtask.data.dataSources.localStore.VGTaskDataStore
import com.app.vgtask.data.dataSources.remote.UsersRemoteService
import com.app.vgtask.data.models.Trip
import com.app.vgtask.data.models.User
import javax.inject.Inject

class UserRepo @Inject constructor(private val localStore: VGTaskDataStore, private val remoteService: UsersRemoteService) {

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
        val trips = if (user.trips != null ) user.trips.toMutableList() else mutableListOf()
        trips.add(trip)
        remoteService.updateUser(userId, user.copy(trips = trips))
    }

    suspend fun updateUser(user: User){
        remoteService.updateUser(user.id!!, user)
    }
}