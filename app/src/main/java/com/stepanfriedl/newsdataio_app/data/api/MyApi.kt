package com.stepanfriedl.newsdataio_app.data.api

import android.telecom.Call
import com.stepanfriedl.newsdataio_app.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {

    @GET("api/1/latest")
    fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("page") page: String? = null
    ): retrofit2.Call<NewsResponse>
}