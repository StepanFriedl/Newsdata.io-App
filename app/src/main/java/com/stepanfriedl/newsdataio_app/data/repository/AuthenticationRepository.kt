package com.stepanfriedl.newsdataio_app.data.repository

interface AuthenticationRepository {
    suspend fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    suspend fun logout(
        action: () -> Unit
    )

    suspend fun hasTokenStored(): Boolean

    suspend fun getToken(): String?
}