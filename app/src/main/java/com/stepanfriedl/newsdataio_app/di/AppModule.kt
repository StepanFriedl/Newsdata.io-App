package com.stepanfriedl.newsdataio_app.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.stepanfriedl.newsdataio_app.data.repository.AuthenticationRepository
import com.stepanfriedl.newsdataio_app.data.repository.AuthenticationRepositoryImpl
import com.stepanfriedl.newsdataio_app.data.repository.NewsRepository
import com.stepanfriedl.newsdataio_app.data.repository.NewsRepositoryImpl
import com.stepanfriedl.newsdataio_app.ui.home.HomeViewModel
import com.stepanfriedl.newsdataio_app.ui.login.LoginViewModel
import com.stepanfriedl.newsdataio_app.utils.TokenManager
import com.stepanfriedl.newsdataio_app.utils.TokenManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore(name = "user_prefs")

val appModule = module {

    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }

    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(get())
    }

    single {
        androidContext().dataStore
    }

    single {
        TokenManagerImpl(get())
    }

    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        HomeViewModel(get(), get())
    }
}

