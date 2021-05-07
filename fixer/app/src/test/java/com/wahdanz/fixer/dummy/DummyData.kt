package com.wahdanz.fixer.dummy

import com.wahdanz.fixer.data.model.CurrencyModel
import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.entity.FixerEntity

object DummyData {
    val dummyDomainObject = FixerEntity(
        date = "2021-05-06", listCurrencies = listOf(
            CurrencyEntity(
                "USD", 1.2, "2021-05-06"
            ), CurrencyEntity(
                "AUD", 1.2, "2021-05-06"
            ), CurrencyEntity(
                "CAD", 1.2, "2021-05-06"
            ), CurrencyEntity(
                "PLN", 1.2, "2021-05-06"
            ), CurrencyEntity(
                "EGP", 1.2, "2021-05-06"
            )
        )
    )
    val dummyDataObject = FixerModel(
        date = "2021-05-06", listCurrencys = listOf(
            CurrencyModel(
                "USD", 1.2, "2021-05-06"
            ), CurrencyModel(
                "AUD", 1.2, "2021-05-06"
            ), CurrencyModel(
                "CAD", 1.2, "2021-05-06"
            ), CurrencyModel(
                "PLN", 1.2, "2021-05-06"
            ), CurrencyModel(
                "EGP", 1.2, "2021-05-06"
            )
        )
    )
    val dummyUSD = CurrencyEntity(
        "USD", 1.2, "2021-05-06"
    )
}
