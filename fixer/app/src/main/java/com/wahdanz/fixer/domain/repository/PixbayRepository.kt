package com.wahdanz.fixer.domain.repository

import com.wahdanz.fixer.domain.entity.CurrencyEntity

interface FixerRepository {
    suspend fun getAllCurrencies(date: List<String>): List<CurrencyEntity>
    suspend fun getCurrency(currency: String): CurrencyEntity
}