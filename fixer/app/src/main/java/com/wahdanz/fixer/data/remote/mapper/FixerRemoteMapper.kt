package com.wahdanz.fixer.data.remote.mapper

import com.wahdanz.fixer.core.remote.mapper.RemoteMapper
import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.data.model.CurrencyModel
import com.wahdanz.fixer.data.remote.model.FixerResponse

class FixerRemoteMapper :
    RemoteMapper<FixerResponse, FixerModel> {

    override fun mapFromRemote(type: FixerResponse): FixerModel {
        return FixerModel(
            date = type.date,
            listCurrencys = type.rates.map { CurrencyModel(value = it.value, currency =
            it.key, date = type.date) }
        )
    }
}