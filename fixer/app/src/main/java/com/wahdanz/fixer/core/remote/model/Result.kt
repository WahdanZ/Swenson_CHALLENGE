package com.wahdanz.fixer.core.remote.model

data class Result<out T : Any>(val data: T?)
