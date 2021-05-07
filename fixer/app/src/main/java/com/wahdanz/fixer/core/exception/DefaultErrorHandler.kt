package com.wahdanz.fixer.core.exception

import com.wahdanz.fixer.R
import com.wahdanz.fixer.core.remote.exception.NetworkException
import com.wahdanz.fixer.core.resource.AppResources
import java.io.IOException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

internal class DefaultErrorHandler constructor(private val resourceManager: AppResources) : ErrorHandler {
    override fun getErrorMessage(error: Throwable): String {

        return when (error) {
            is SocketTimeoutException -> {
                resourceManager.getString(R.string.timeout_error_message)
            }
            is IOException, is UnknownHostException, is SocketException
            -> resourceManager.getString(R.string.no_internet_connection)

            is NetworkException -> {
                error.error ?: resourceManager.getString(R.string.unknown_error)
            }
            else -> resourceManager.getString(R.string.unknown_error)
        }
    }
}