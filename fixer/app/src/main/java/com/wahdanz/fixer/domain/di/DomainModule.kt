package com.wahdanz.fixer.domain.di

import com.wahdanz.fixer.domain.interactors.GetAllCurrenciesUseCase
import com.wahdanz.fixer.domain.interactors.GetCurrencyUseCase
import org.koin.dsl.module

val domainModule = module(override = true) {
    factory { GetAllCurrenciesUseCase(get()) }
    factory { GetCurrencyUseCase(get()) }
}