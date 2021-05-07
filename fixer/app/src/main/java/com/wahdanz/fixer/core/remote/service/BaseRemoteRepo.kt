package com.wahdanz.fixer.core.remote.service

import com.wahdanz.fixer.core.remote.model.Result
import com.squareup.moshi.Moshi
import com.wahdanz.fixer.core.remote.exception.NetworkException
import retrofit2.Response
abstract class BaseRemoteRepo(private val moshi: Moshi) {
    fun <T : Any> execute(call: Response<T>): Result<T> {
            if (call.isSuccessful)
             return Result(call.body())
             else
             throw NetworkException(moshi.adapter(String::class.java).fromJson(call.errorBody()?.string() ?: "Bazinga!!"))
    }
}