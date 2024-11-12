package com.stepanfriedl.newsdataio_app.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class TokenManagerImpl(
    private val dataStore: DataStore<Preferences>
) : TokenManager {
    private val TOKEN_KEY = stringPreferencesKey("auth_token")

    override val authToken: Flow<String?> = dataStore.data
        .map { preferences -> preferences[TOKEN_KEY] }

    override suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    override suspend fun hasTokenStored(): Boolean =
        authToken.firstOrNull() != null

}