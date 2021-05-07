package com.wahdanz.fixer.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.executor.ExecutionThread
import com.wahdanz.fixer.domain.interactors.GetCurrencyUseCase
import com.wahdanz.fixer.extensions.debugLog
import com.wahdanz.fixer.extensions.errorLog
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CurrencyConvertViewModel(
    private val errorHandler: ErrorHandler,
    private val executionThread: ExecutionThread,
    private val getFixerUseCase: GetCurrencyUseCase
) : ViewModel(), CoroutineScope {
    private val job = Job()
    val state: MutableLiveData<CurrencyConvertState> = MutableLiveData()
    override val coroutineContext: CoroutineContext
        get() = executionThread.mainScheduler + job

    fun convertCurrency(currency: String, amount: Double) {
        if (state.value == CurrencyConvertState.Loading)
            return
        launch(coroutineContext) {
            state.value =
                CurrencyConvertState.Loading
            try {
                val result = withContext(executionThread.ioScheduler) {
                    getFixerUseCase.execute(GetCurrencyUseCase.Params(currency))
                }
                val value = result.value * amount
                debugLog("convertCurrency $currency : $result")
                state.value =
                    CurrencyConvertState.Update(
                        value
                    )
            } catch (e: Exception) {
                errorLog("\"convertCurrency $currency : ", e)

                state.value =
                    CurrencyConvertState.Error(
                        errorHandler.getErrorMessage(e)
                    )
            }
        }
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}