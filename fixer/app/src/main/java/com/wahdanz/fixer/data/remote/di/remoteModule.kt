package com.wahdanz.fixer.data.remote.di

import com.squareup.moshi.Moshi
import com.wahdanz.fixer.core.remote.network.RetrofitFactory
import com.wahdanz.fixer.data.remote.FixerApiInterface
import com.wahdanz.fixer.data.remote.FixerRemoteImpl
import com.wahdanz.fixer.data.remote.mapper.FixerRemoteMapper
import com.wahdanz.fixer.data.store.FixerRemote
import org.koin.dsl.module
import retrofit2.Retrofit

val remoteModule = module(override = true) {
    single { Moshi.Builder().build() }
    single { RetrofitFactory.makeRetrofit(true) }
    factory { get<Retrofit>().create(FixerApiInterface::class.java) }
    factory { FixerRemoteMapper() }
    factory<FixerRemote> { FixerRemoteImpl(get(), get(), get()) }
}