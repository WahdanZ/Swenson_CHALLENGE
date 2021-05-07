package com.wahdanz.fixer.data.model

import com.wahdanz.fixer.core.data.mapper.DataModel

data class FixerModel(
    val date: String = "",
    val listCurrencys: List<CurrencyModel> = listOf()
) : DataModel

data class CurrencyModel(val currency: String, val value: Double, val date: String?) : DataModel
