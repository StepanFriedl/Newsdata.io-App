package com.stepanfriedl.newsdataio_app.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.ui.login.LoginViewModel
import com.stepanfriedl.newsdataio_app.utils.TokenManager
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.getKoin
import com.stepanfriedl.newsdataio_app.R
import com.stepanfriedl.newsdataio_app.ui.components.ArticleRow
import com.stepanfriedl.newsdataio_app.ui.theme.ErrorRed

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = koinViewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val articles = viewModel.articles.collectAsState()
    val showDialog = viewModel.showDialog.value
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        viewModel.getArticles()
    }

    LaunchedEffect(scrollState) {
        snapshotFlow { scrollState.value to scrollState.maxValue }
            .collect { (scrollPosition, maxValue) ->
                if (scrollPosition == maxValue && maxValue != 0) {
                    println("Fetch A")
                    viewModel.getNextPageArticles()
                } else if (maxValue == 0) {
                    println("Fetch B")
                    viewModel.getNextPageArticles()
                }
            }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.background_home),
                contentScale = ContentScale.Crop,
                alpha = 0.25f
            ),
    ) {
        articles.value?.let { articles ->
            if (articles.count() == 0) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Nothing to display")

                    Spacer(modifier = Modifier.height(25.dp))

                    IconButton(
                        onClick = {
                            viewModel.retryAction?.let {
                                coroutineScope.launch {
                                    it()
                                }
                            }
                        },
                        modifier = Modifier
                            .background(
                                Color.Black.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(5.dp)
                            )
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.replay),
                            contentDescription = "Try Again",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(50.dp))
                    articles.forEach { article ->
                        ArticleRow(
                            article = article
                        ) {

                        }
//                        Text(text = article.title)
//                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        } ?: run {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Loading, please wait...")
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Top
        ) {
            IconButton(
                onClick = {
                    coroutineScope.launch {
                        viewModel.logout {
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier
                    .background(
                        ErrorRed.copy(alpha = 0.9f),
                        shape = RoundedCornerShape(5.dp)
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = {},
                title = { Text("Error") },
                text = { Text("The data couldn't be downloaded. Check your internet connection. If the problem persists, the server's limit might be reached.") },
                confirmButton = {
                    Button(onClick = {
                        viewModel.retryAction?.let {
                            coroutineScope.launch { it() }
                        }
                    }) {
                        Text("Retry")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        viewModel.dismissDialog()
                    }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}