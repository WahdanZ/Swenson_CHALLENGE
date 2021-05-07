package com.wahdanz.fixer.data.store

import com.wahdanz.fixer.data.model.FixerModel

interface FixerRemote {
    suspend fun getExchangeRate(key: String, currency: String): FixerModel
}