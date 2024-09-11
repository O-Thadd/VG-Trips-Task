package com.app.vgtask.data.dataSources.localStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.app.vgtask.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class VGTaskDataStore(private val context: Context) {

    private val userIdKey = stringPreferencesKey("userIdKey")

    suspend fun getUserId(): String? {
        return context.dataStore.data.map { it[userIdKey] }.first()
    }

    suspend fun updateUserId(id: String){
        context.dataStore.edit { it[userIdKey] = id }
    }
}