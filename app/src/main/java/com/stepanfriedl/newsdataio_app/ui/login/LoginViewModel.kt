package com.stepanfriedl.newsdataio_app.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.stepanfriedl.newsdataio_app.data.repository.AuthenticationRepository
import com.stepanfriedl.newsdataio_app.data.repository.NewsRepository
import com.stepanfriedl.newsdataio_app.utils.AuthConstants

class LoginViewModel(
    private val authRepository: AuthenticationRepository
) : ViewModel() {

    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var showError = mutableStateOf(false)

    suspend fun performLogin(
        onSuccess: () -> Unit
    ) {
        val onFailure = {
            showError.value = true
        }
        authRepository.login(
            username = username.value,
            password = password.value,
            onSuccess = onSuccess,
            onFailure = onFailure
        )
    }

    suspend fun checkToken(
        tokenAvailableAction: () -> Unit,
        tokenNotAvailableAction: () -> Unit
    ) {
        if (authRepository.hasTokenStored()) {
            tokenAvailableAction()
        } else {
            tokenNotAvailableAction()
        }
    }
}