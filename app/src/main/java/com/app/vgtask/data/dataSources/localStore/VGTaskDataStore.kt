package com.app.vgtask.data.dataSources.localStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.vgtask.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VGTaskDataStore @Inject constructor(private val context: Context) {

    private val userIdKey = stringPreferencesKey("userIdKey")

    suspend fun getUserId(): String? {
        return context.dataStore.data.map { it[userIdKey] }.first()
    }

    suspend fun updateUserId(id: String){
        context.dataStore.edit { it[userIdKey] = id }
    }
}