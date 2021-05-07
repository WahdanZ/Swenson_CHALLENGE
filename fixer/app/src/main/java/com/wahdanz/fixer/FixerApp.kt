package com.wahdanz.fixer

import android.app.Application
import com.blongho.country_data.World
import com.wahdanz.fixer.core.di.appModule
import com.wahdanz.fixer.data.cashe.di.cacheModule
import com.wahdanz.fixer.data.di.dataModule
import com.wahdanz.fixer.data.remote.di.remoteModule
import com.wahdanz.fixer.domain.di.domainModule
import com.wahdanz.fixer.presentation.di.presentationModule

import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

import org.koin.core.context.startKoin

class FixerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FixerApp)
            World.init(getApplicationContext()); // Initializes the libray and loads all data

            modules(listOf(dataModule, remoteModule,
                cacheModule, appModule, domainModule, presentationModule))
        }
    }
}