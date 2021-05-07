package com.wahdanz.fixer.data.cashe

import com.wahdanz.fixer.data.cashe.db.PixabayDatabase
import com.wahdanz.fixer.data.cashe.mapper.CurrencyCacheMapper
import com.wahdanz.fixer.data.model.CurrencyModel
import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.data.store.FixerCache

class FixerCacheImpl(
    private val db: PixabayDatabase,
    private val currencyCacheMapper: CurrencyCacheMapper
) : FixerCache {

    override suspend fun getAllCurrencies(date: String?): List<CurrencyModel> {
        return  db.cachedPixabayDao().getAllCurrencies().groupBy { it.date }.values.firstOrNull()
            ?.map(currencyCacheMapper::mapFromCached) ?: emptyList()
    }

    override suspend fun getCurrency(currency: String): CurrencyModel {
        return currencyCacheMapper.mapFromCached(
            db.cachedPixabayDao().getCurrency(currency = currency)
        )
    }

    override suspend fun saveAllCurrencies(fixer: FixerModel) {
        return db.cachedPixabayDao()
            .saveAllCurrencies(fixer.listCurrencys.map(currencyCacheMapper::mapToCached).map { it.copy(date = fixer.date) })
    }
}