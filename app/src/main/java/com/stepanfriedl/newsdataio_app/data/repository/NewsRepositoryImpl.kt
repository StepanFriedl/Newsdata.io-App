package com.stepanfriedl.newsdataio_app.data.repository

import com.stepanfriedl.newsdataio_app.data.NetworkConstants
import com.stepanfriedl.newsdataio_app.data.api.MyApi
import com.stepanfriedl.newsdataio_app.data.model.Article
import com.stepanfriedl.newsdataio_app.data.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepositoryImpl(
    private val authenticationRepository: AuthenticationRepository
) : NewsRepository {
    private var articles: MutableList<Article>? = null
    private var nextPage: String? = null

    override suspend fun getNews(onSuccess: (List<Article>?) -> Unit, onFailure: () -> Unit) {
        articles?.let {
            onSuccess(it)
        } ?: run {
            fetchNews(
                onSuccess = {
                    articles = it?.toMutableList()
                    onSuccess(it)
                },
                onFailure = onFailure
            )
        }
    }

    override suspend fun fetchNextPageNews(
        onSuccess: (List<Article>?) -> Unit,
        onFailure: () -> Unit
    ) {
        fetchNews(
            onSuccess = { newArticles ->
                if (articles == null) {
                    articles = newArticles?.toMutableList()
                    onSuccess(newArticles)
                } else {
                    val filteredNewArticles = newArticles?.filter { newArticle ->
                        articles?.none { existingArticle ->
                            existingArticle.article_id == newArticle.article_id
                        } ?: true
                    }
                    filteredNewArticles?.let {
                        articles?.addAll(it)
                    }
                    onSuccess(filteredNewArticles)
                }
            },
            onFailure = onFailure,
            page = nextPage
        )
    }

    private suspend fun fetchNews(
        onSuccess: (List<Article>?) -> Unit,
        onFailure: () -> Unit,
        page: String? = null
    ) {
        val api = Retrofit.Builder()
            .baseUrl(NetworkConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getNews(
            apiKey = authenticationRepository.getToken() ?: "",
            page = page
        ).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>
            ) {
                if (response.isSuccessful) {
                    nextPage = response.body()?.nextPage
                    onSuccess(response.body()?.results)
                } else {
                    onFailure()
                }
            }

            override fun onFailure(
                call: Call<NewsResponse?>,
                t: Throwable
            ) {
                onFailure()
            }
        })
    }

}