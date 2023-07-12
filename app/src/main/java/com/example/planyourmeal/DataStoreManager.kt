package com.example.planyourmeal

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
class DataStoreManager(val context: Context) {
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("phone_number")] = settingsData.phoneNumber
            pref[stringPreferencesKey("name")] = settingsData.name
            pref[stringPreferencesKey("surname")] = settingsData.surname
        }
    }

    fun getSettings() = context.dataStore.data.map { pref ->
        return@map SettingsData(
        pref[stringPreferencesKey("phone_number")] ?: "79267164351",
        pref[stringPreferencesKey("name")] ?: "Ivan",
        pref[stringPreferencesKey("surname")] ?: "Ivanov"
        )
    }
}