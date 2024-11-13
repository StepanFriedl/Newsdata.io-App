package com.stepanfriedl.newsdataio_app.ui.home

import androidx.lifecycle.ViewModel
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.data.repository.AuthenticationRepository
import com.stepanfriedl.newsdataio_app.data.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel (
    private val authRepository: AuthenticationRepository,
    private val newsRepository: NewsRepository
): ViewModel() {

    private val _articles = MutableStateFlow<List<Article>?>(null)
    val articles: StateFlow<List<Article>?> = _articles

    suspend fun logout(
        action: () -> Unit
    ) {
        authRepository.logout {
            action()
        }
    }

    suspend fun getArticles() {
        newsRepository.getNews(
            onSuccess = {
                _articles.value = it?.toMutableList()
            },
            onFailure = {
                // TODO: - Add error message
            }
        )
    }

    suspend fun getNextPageArticles() {
        newsRepository.fetchNextPageNews(
            onSuccess = {
                it?.let {
                    _articles.value = (_articles.value ?: emptyList()) + it
                }
            },
            onFailure = {
                // TODO: - Add error message
            }
        )
    }
}