package com.wahdanz.fixer.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahdanz.fixer.Constants
import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.executor.ExecutionThread
import com.wahdanz.fixer.domain.entity.CurrencyEntity
import com.wahdanz.fixer.domain.interactors.GetAllCurrenciesUseCase
import com.wahdanz.fixer.extensions.debugLog
import com.wahdanz.fixer.extensions.errorLog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeViewModel(
    private val errorHandler: ErrorHandler,
    private val executionThread: ExecutionThread,

    private val getAllFixersUseCase: GetAllCurrenciesUseCase
) : ViewModel(), CoroutineScope {
    private val job = Job()
    val state: MutableLiveData<FixerHomeState> = MutableLiveData()

    init {
        getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
    }

    override val coroutineContext: CoroutineContext
        get() = executionThread.mainScheduler + job

    fun getAllCurrencies(list: List<String> = listOf("USD","AUD","CAD","PLN","EGP")) {
        if (state.value == FixerHomeState.Loading)
            return
        launch(coroutineContext) {
            state.value =
                FixerHomeState.Loading
            try {
                debugLog("getAllCurrencies  ${list.joinToString(",")} ")
                val result = getFixerList(list)
                debugLog("getAllCurrencies $result")
                state.value =
                        FixerHomeState.FixerData(
                            data = result
                        )
            } catch (e: Exception) {
                errorLog("getAllCurrencies  ${list.joinToString(",")} ", e)
                state.value =
                    FixerHomeState.Error(
                        errorHandler.getErrorMessage(e)
                    )
            }
        }
    }

    private suspend fun getFixerList(list: List<String>): List<CurrencyEntity> = withContext(executionThread.ioScheduler) {
        getAllFixersUseCase.execute(
            GetAllCurrenciesUseCase.Params(
                list
            )
        )
    }


    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

}