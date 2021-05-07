package com.wahdanz.fixer.domain.entity

import com.wahdanz.fixer.core.domain.Entity

data class FixerEntity(
    val date: String,
    val listCurrencies: List<CurrencyEntity> = listOf()
) : Entity

data class CurrencyEntity(
    val currency: String = "",
    val value: Double,
    val date: String?
) : Entity
