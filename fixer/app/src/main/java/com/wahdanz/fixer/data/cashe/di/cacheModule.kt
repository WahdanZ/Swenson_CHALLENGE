package com.wahdanz.fixer.data.cashe.di

import com.wahdanz.fixer.data.cashe.FixerCacheImpl
import com.wahdanz.fixer.data.cashe.db.PixabayDatabase
import com.wahdanz.fixer.data.cashe.mapper.CurrencyCacheMapper
import com.wahdanz.fixer.data.store.FixerCache
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val cacheModule = module(override = true) {
    single<FixerCache> { FixerCacheImpl(get(), get()) }
    factory { CurrencyCacheMapper()  }
    single { PixabayDatabase.getInstance(context = androidContext()) }
}