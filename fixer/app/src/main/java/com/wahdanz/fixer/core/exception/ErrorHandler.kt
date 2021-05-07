package com.wahdanz.fixer.core.exception
interface ErrorHandler {
    fun getErrorMessage(error: Throwable): String
}