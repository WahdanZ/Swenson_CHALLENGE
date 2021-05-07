package com.wahdanz.fixer.core.executor

import kotlinx.coroutines.CoroutineDispatcher

interface ExecutionThread {
    val mainScheduler: CoroutineDispatcher
    val ioScheduler: CoroutineDispatcher
}