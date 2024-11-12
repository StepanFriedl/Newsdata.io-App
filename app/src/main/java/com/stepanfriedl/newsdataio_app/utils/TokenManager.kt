package com.stepanfriedl.newsdataio_app.utils

import kotlinx.coroutines.flow.Flow

interface TokenManager {
    val authToken: Flow<String?>
    suspend fun saveToken(token: String)
    suspend fun clearToken()
    suspend fun hasTokenStored(): Boolean
}