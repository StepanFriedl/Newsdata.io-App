package com.stepanfriedl.newsdataio_app.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import com.stepanfriedl.newsdataio_app.R
import com.stepanfriedl.newsdataio_app.ui.components.ConfirmationButton
import com.stepanfriedl.newsdataio_app.ui.components.ErrorTextLabel
import com.stepanfriedl.newsdataio_app.ui.components.LoginTextInput
import com.stepanfriedl.newsdataio_app.ui.home.HomeViewModel
import kotlinx.coroutines.launch



@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = koinViewModel<LoginViewModel>()
    val homeViewMode = koinViewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        viewModel.checkToken {
            navController.navigate("Home")
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background_login),
                contentScale = ContentScale.Crop
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LoginTextInput(
            text = viewModel.username,
            onTextChangedAction = { viewModel.showError.value = false }
        )

        Spacer(modifier = Modifier.height(25.dp))

        LoginTextInput(
            text = viewModel.password,
            isPassword = true,
            onTextChangedAction = { viewModel.showError.value = false }
        )

        ErrorTextLabel(
            text = "Incorrect username or password. Please try again.",
            showError = viewModel.showError
        )

        Spacer(modifier = Modifier.height(5.dp))

        ConfirmationButton(title = "Log in") {
            coroutineScope.launch {
                viewModel.performLogin {
                    navController.navigate("Home")
                }
            }
        }
    }
}
