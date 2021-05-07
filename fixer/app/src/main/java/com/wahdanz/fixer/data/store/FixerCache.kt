package com.wahdanz.fixer.data.store

import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.data.model.CurrencyModel

interface FixerCache {
    suspend fun getAllCurrencies(date: String?): List<CurrencyModel>
    suspend fun getCurrency(currency: String): CurrencyModel
    suspend fun saveAllCurrencies(fixer: FixerModel)
}