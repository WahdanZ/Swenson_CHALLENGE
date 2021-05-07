package com.wahdanz.fixer.core.resource

import com.wahdanz.fixer.R

class ResourcesRepository(private val appResources: AppResources) {

    fun getNetworkExceptionMessage(): String = appResources.getString(R.string.no_internet_connection)

    fun getSocketTimeoutExceptionMessage(): String = appResources.getString(R.string.timeout_error_message)
}