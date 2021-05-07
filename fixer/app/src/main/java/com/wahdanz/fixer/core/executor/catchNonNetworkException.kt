package com.wahdanz.fixer.core.executor

import com.wahdanz.fixer.core.remote.exception.NetworkException

suspend inline fun <reified T> catchNonNetworkException(
    noinline networkCall: suspend () -> T,
    noinline cacheCall: suspend (e: Exception) -> T
): T {
    return try {
        networkCall.invoke()
    } catch (e: NetworkException) {
        throw e
    } catch (e: Exception) {
        cacheCall.invoke(e)
    }
}