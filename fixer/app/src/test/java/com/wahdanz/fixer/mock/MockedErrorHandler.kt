package com.wahdanz.fixer.mock

import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.remote.exception.NetworkException
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class MockedErrorHandler : ErrorHandler {
    override fun getErrorMessage(error: Throwable): String {

        return when (error) {
            is SocketTimeoutException -> "Connection timeout"
            is IOException, is UnknownHostException, is SocketException
            -> "No internet connection"

            is NetworkException -> error.error ?: "Unknown error"

            else -> "Unknown error"
        }
    }
}