package com.wahdanz.fixer.data.remote

import com.wahdanz.fixer.data.remote.model.FixerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FixerApiInterface {

    @GET("/api/latest?")
    suspend fun getExchangeRate(
        @Query(value = "access_key") key: String,
        @Query(value = "symbols") currencies: String
    ): Response<FixerResponse>
}