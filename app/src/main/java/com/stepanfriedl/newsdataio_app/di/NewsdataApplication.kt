package com.stepanfriedl.newsdataio_app.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsdataApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsdataApplication)
            modules(appModule)
        }
    }
}