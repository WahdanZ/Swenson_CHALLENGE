package com.wahdanz.fixer.presentation.home

import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.entity.FixerEntity

sealed class FixerHomeState {
    object Loading : FixerHomeState()
    data class FixerData(val data: List<CurrencyEntity>) : FixerHomeState()
    data class Error(val errorMsg: String, val data: List<CurrencyEntity> = listOf()) : FixerHomeState()
}