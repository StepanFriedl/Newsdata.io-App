package com.stepanfriedl.newsdataio_app.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.stepanfriedl.newsdataio_app.R
import com.stepanfriedl.newsdataio_app.ui.home.HomeViewModel
import com.stepanfriedl.newsdataio_app.ui.theme.LightGray
import com.stepanfriedl.newsdataio_app.utils.DateUtils
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import coil.compose.AsyncImage
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.ui.components.ArticleRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    articleId: String
) {
    val viewModel = koinViewModel<DetailsViewModel>()
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    viewModel.getArticle(id = articleId)?.let { article ->
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            DateUtils.formatDate(
                                inputDate = article.pubDate,
                                inputFormat = "yyyy-MM-dd HH:mm:ss",
                                outputFormat = "dd. MM. yyyy HH:mm"
                            ),
                            fontSize = 15.sp
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Back button"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.shareUrl(
                                context,
                                article.source_url
                            )
                        }) {
                            Icon(
                                Icons.Filled.Share,
                                contentDescription = "Share icon"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = LightGray,
                        actionIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        navigationIconContentColor = Color.White
                    )
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .verticalScroll(scrollState)
                    .background(LightGray.copy(alpha = 0.2f)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                article.image_url?.let {
                    AsyncImage(
                        model = it,
                        contentDescription = "Article image",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillWidth
                    )
                }

                Text(
                    text = article.title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp,
                            bottom = 10.dp
                        )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top
                ) {
                    article.creator?.let {
                        Column(
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = "Authors:",
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                            it.forEach { author ->
                                Text(
                                    text = author,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.End
                    ) {
                        article.language?.let { language ->
                            Text(
                                text = "Language:",
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                            Text(
                                text = language,
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )

                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        article.link?.let { link ->
                            Text(
                                text = "Link:",
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                            IconButton(
                                onClick = {

                                    viewModel.openLink(
                                        context = context,
                                        url = link
                                    )
                            },
                                modifier = Modifier.height(25.dp)
                                ) {
                                Image(
                                    painter = painterResource(id = R.drawable.link),
                                    contentDescription = "Link button",
                                    modifier = Modifier.height(25.dp),
                                    contentScale = ContentScale.Inside
                                )
                            }
                        }
                    }
                }

                Text(
                    text = article.description ?: "",
                    fontSize = 12.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(horizontal = 30.dp)
                )
            }
        }
    }
}