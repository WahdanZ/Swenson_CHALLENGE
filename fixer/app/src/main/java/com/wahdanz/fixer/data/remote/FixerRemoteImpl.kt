package com.wahdanz.fixer.data.remote

import com.squareup.moshi.Moshi
import com.wahdanz.fixer.core.remote.service.BaseRemoteRepo
import com.wahdanz.fixer.data.model.FixerModel
import com.wahdanz.fixer.data.remote.mapper.FixerRemoteMapper
import com.wahdanz.fixer.data.store.FixerRemote

class FixerRemoteImpl(
    private val fixerApiInterface: FixerApiInterface,
    moshi: Moshi,
    private val remoteMapper: FixerRemoteMapper
) : BaseRemoteRepo(moshi), FixerRemote {

    override suspend fun getExchangeRate(key: String, currencies: String): FixerModel {
        val x = execute(
            fixerApiInterface.getExchangeRate(
                key = key,
                currencies = currencies
            )
        )
            .data
        return remoteMapper.mapFromRemote(x!!)
    }
}