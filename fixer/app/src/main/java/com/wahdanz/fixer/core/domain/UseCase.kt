package com.wahdanz.fixer.core.domain
interface UseCase<P, R> {
    suspend fun execute(param: P? = null): R
}