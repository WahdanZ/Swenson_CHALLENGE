package com.wahdanz.fixer.presentation.di

import com.wahdanz.fixer.presentation.details.CurrencyConvertViewModel
import com.wahdanz.fixer.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module(override = true) {
    viewModel {
        HomeViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        CurrencyConvertViewModel(
            get(),
            get(),
            get()
        )
    }
}