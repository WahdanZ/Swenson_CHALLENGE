package com.wahdanz.fixer.data.cashe.mapper

import com.wahdanz.fixer.core.cache.mapper.CacheMapper
import com.wahdanz.fixer.data.cashe.dto.CurrencyCache
import com.wahdanz.fixer.data.cashe.dto.FixerCache
import com.wahdanz.fixer.data.model.CurrencyModel
import com.wahdanz.fixer.data.model.FixerModel


//class FixerCacheMapper :
//    CacheMapper<FixerCache, FixerModel> {
//
//    override fun mapFromCached(type: FixerCache?): FixerModel {
//        return FixerModel(
//            date = type?.date ?: "",
//            listCurrencys = type?.listCurrencies?.map { CurrencyModel(it.currency, it.value, type.date) } ?: listOf()
//        )
//    }
//
//    override fun mapToCached(type: FixerModel?): FixerCache {
//        return FixerCache(
//            date = type?.date ?: "",
//            listCurrencies = type?.listCurrencys?.map { CurrencyCache(currency = it.currency!!, value = it.value, date = type.date) }
//                ?: listOf()
//
//        )
//    }
//}

class CurrencyCacheMapper :
    CacheMapper<CurrencyCache, CurrencyModel> {

    override fun mapFromCached(type: CurrencyCache?): CurrencyModel {
        return CurrencyModel(type!!.currency, type.value, type.date)
    }

    override fun mapToCached(type: CurrencyModel?): CurrencyCache {
        return CurrencyCache(currency = type?.currency!!, value = type.value, date = type.date!!)
    }
}