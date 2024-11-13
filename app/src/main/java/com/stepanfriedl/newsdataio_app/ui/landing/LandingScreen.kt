package com.stepanfriedl.newsdataio_app.ui.landing

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.stepanfriedl.newsdataio_app.ui.login.LoginViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LandingScreen(navController: NavController) {
    val loginViewModel = koinViewModel<LoginViewModel>()

    LaunchedEffect(Unit) {
        loginViewModel.checkToken(
            tokenAvailableAction = {
                navController.navigate("Home")
            },
            tokenNotAvailableAction = {
                navController.navigate("Login")
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}