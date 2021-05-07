package com.wahdanz.fixer.core.di

import com.wahdanz.fixer.core.exception.DefaultErrorHandler
import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.executor.CoroutineExecutor
import com.wahdanz.fixer.core.executor.ExecutionThread
import com.wahdanz.fixer.core.resource.AppResources
import com.wahdanz.fixer.core.resource.ResourcesRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { AppResources(context = androidApplication()) }
    single { ResourcesRepository(get()) }
    factory<ErrorHandler> {
        DefaultErrorHandler(get())
    }
    factory<ExecutionThread> { CoroutineExecutor() }
}