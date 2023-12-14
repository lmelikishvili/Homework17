package com.example.homework17.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class UserManager(context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
    private val mDataStore: DataStore<Preferences> = context.dataStore

    companion object {
        val USER_EMAIL_KEY = stringPreferencesKey("USER_EMAIL")
    }

    suspend fun saveUserMail(email: String) {
        mDataStore.edit { preferences ->
            preferences[USER_EMAIL_KEY] = email

        }
    }

    val userEmaiFlow: Flow<String> = mDataStore.data.map {
        it[USER_EMAIL_KEY] ?: ""
    }

    suspend fun clearDataStore(context: Context) {
        mDataStore.edit { preferences ->
            preferences.clear()
        }
    }
}