package com.wahdanz.fixer.data.di

import com.wahdanz.fixer.data.FixerRepositoryImpl
import com.wahdanz.fixer.data.mapper.CurrencyDataMapper
import com.wahdanz.fixer.domain.repository.FixerRepository
import org.koin.dsl.module

val dataModule = module(override = true) {
    factory { CurrencyDataMapper() }
    factory<FixerRepository> { FixerRepositoryImpl(get(), get(), get()) }
}