package com.wahdanz.fixer.data.remote.model

import com.squareup.moshi.Json
import com.wahdanz.fixer.core.remote.mapper.RemoteModel

data class FixerResponse(
    @Json(name = "base")
    val base: String, // EUR
    @Json(name = "date")
    val date: String, // 2021-05-06
    @Json(name = "rates")
    val rates: Map<String, Double>,
    @Json(name = "success")
    val success: Boolean, // true
    @Json(name = "timestamp")
    val timestamp: Int // 1620320043
) : RemoteModel
