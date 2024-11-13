package com.stepanfriedl.newsdataio_app.ui.home

import androidx.compose.runtime.mutableStateOf
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
    private val _showDialog = mutableStateOf(false)
    val showDialog: androidx.compose.runtime.State<Boolean> = _showDialog
    var retryAction: (suspend () -> Unit)? = null

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
                showRetryDialog {
                    getArticles()
                }
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
                showRetryDialog {
                    getNextPageArticles()
                }
            }
        )
    }

    fun dismissDialog() {
        _showDialog.value = false
        if (_articles.value == null) {
            _articles.value = emptyList<Article>()
        }
    }

    fun showRetryDialog(retryAction: suspend () -> Unit) {
        this.retryAction = {
            _showDialog.value = false
            retryAction()
        }
        _showDialog.value = true
    }
}