package com.stepanfriedl.newsdataio_app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.stepanfriedl.newsdataio_app.ui.login.LoginViewModel
import com.stepanfriedl.newsdataio_app.utils.TokenManager
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = koinViewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Home")

        Button(onClick = {
            coroutineScope.launch {
                viewModel.logout {
                    try {
                        navController.popBackStack()
                    } catch (e: IllegalArgumentException) {

                    }
                }
            }

        }) {
            Text("Logout")
        }

        Button(onClick = {
            navController.popBackStack()
        }) {
            Text("Go Back")
        }
    }
}