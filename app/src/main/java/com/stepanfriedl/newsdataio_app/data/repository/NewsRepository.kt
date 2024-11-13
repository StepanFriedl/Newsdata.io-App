package com.stepanfriedl.newsdataio_app.data.repository

import com.stepanfriedl.newsdataio_app.data.model.Article

interface NewsRepository {
    suspend fun getNews(onSuccess: (List<Article>?) -> Unit, onFailure: () -> Unit)
    suspend fun fetchNextPageNews(onSuccess: (List<Article>?) -> Unit, onFailure: () -> Unit)
    fun getArticle(id: String): Article?
}