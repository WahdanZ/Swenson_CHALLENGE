package com.wahdanz.fixer.data

import com.wahdanz.fixer.Constants
import com.wahdanz.fixer.core.executor.catchNonNetworkException
import com.wahdanz.fixer.data.mapper.CurrencyDataMapper
import com.wahdanz.fixer.data.store.FixerCache
import com.wahdanz.fixer.data.store.FixerRemote
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.repository.FixerRepository
import com.wahdanz.fixer.extensions.errorLog

class FixerRepositoryImpl(
    private val cache: FixerCache,
    private val remote: FixerRemote,
    private val mapper: CurrencyDataMapper
) :
    FixerRepository {
    override suspend fun getAllCurrencies(date: List<String>): List<CurrencyEntity> {
        return catchNonNetworkException(networkCall = {
            val res = remote.getExchangeRate(currency = date.joinToString(",") , key = Constants.access_key)
            cache.saveAllCurrencies(res)
            res.listCurrencys.map(mapper::mapToEntity)
        }) { exception ->
            errorLog("getExchangeRate ", exception)
            val res = cache.getAllCurrencies("").map(mapper::mapToEntity)
            if (res.isEmpty())
                throw exception
            res
        }
    }

    override suspend fun getCurrency(currency: String): CurrencyEntity {
        return mapper.mapToEntity(cache.getCurrency(currency))
    }
}
