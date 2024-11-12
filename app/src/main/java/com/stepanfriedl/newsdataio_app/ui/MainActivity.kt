package com.stepanfriedl.newsdataio_app.ui

import android.os.Bundle
import android.provider.DocumentsContract.Root
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stepanfriedl.newsdataio_app.ui.details.DetailsScreen
import com.stepanfriedl.newsdataio_app.ui.home.HomeScreen
import com.stepanfriedl.newsdataio_app.ui.login.LoginScreen
import com.stepanfriedl.newsdataio_app.ui.theme.Newsdataio_AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Newsdataio_AppTheme {
                RootView()
            }
        }
    }
}

@Composable
fun RootView() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable(route = "Login") {
            LoginScreen(navController)
        }

        composable(route = "Home") {
            HomeScreen(navController)
        }

        composable(route = "Details") {
            DetailsScreen(navController)
        }

    }
}