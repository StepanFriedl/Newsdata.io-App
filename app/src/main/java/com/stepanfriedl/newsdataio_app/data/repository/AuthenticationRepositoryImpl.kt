package com.stepanfriedl.newsdataio_app.data.repository

import com.stepanfriedl.newsdataio_app.utils.AuthConstants
import com.stepanfriedl.newsdataio_app.utils.TokenManagerImpl

class AuthenticationRepositoryImpl(
    private val tokenManager: TokenManagerImpl
) : AuthenticationRepository {
    override suspend fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        if (username == AuthConstants.HARDCODED_USERNAME && password == AuthConstants.HARDCODED_PASSWORD) {
            tokenManager.saveToken(AuthConstants.HARDCODED_TOKEN)
            onSuccess()
        } else {
            onFailure()
        }
    }

    override suspend fun logout(action: () -> Unit) {
        tokenManager.clearToken()
        action()
    }

    override suspend fun hasTokenStored(): Boolean =
        tokenManager.hasTokenStored()

}