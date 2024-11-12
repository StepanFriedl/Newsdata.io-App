package com.stepanfriedl.newsdataio_app.ui.home

import androidx.lifecycle.ViewModel
import com.stepanfriedl.newsdataio_app.data.repository.AuthenticationRepository

class HomeViewModel (
    private val authRepository: AuthenticationRepository
): ViewModel() {
    suspend fun logout(
        action: () -> Unit
    ) {
        authRepository.logout {
            action()
        }
    }
}