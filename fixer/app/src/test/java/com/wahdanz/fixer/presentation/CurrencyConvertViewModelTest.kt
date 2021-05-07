package com.wahdanz.fixer.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.executor.ExecutionThread
import com.wahdanz.fixer.domain.interactors.GetAllCurrenciesUseCase
import com.wahdanz.fixer.domain.interactors.GetCurrencyUseCase
import com.wahdanz.fixer.dummy.DummyData
import com.wahdanz.fixer.mock.MockedCoroutinesExecutor
import com.wahdanz.fixer.mock.MockedErrorHandler
import com.wahdanz.fixer.presentation.details.CurrencyConvertState
import com.wahdanz.fixer.presentation.details.CurrencyConvertViewModel
import com.wahdanz.fixer.presentation.home.HomeViewModel
import com.wahdanz.fixer.presentation.home.FixerHomeState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CurrencyConvertViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var errorHandler: ErrorHandler
    private lateinit var executionThread: ExecutionThread
    private var getCurrencyUseCase: GetCurrencyUseCase = mock()
    private var currencyConvertViewModel: CurrencyConvertViewModel = mock()
    @Before
    fun setUp() {
        errorHandler = MockedErrorHandler()
        executionThread = MockedCoroutinesExecutor()
        currencyConvertViewModel = CurrencyConvertViewModel(errorHandler, executionThread, getCurrencyUseCase)
    }
    @Test
    fun `test when getting data return state with data`() = runBlocking {
        whenever(getCurrencyUseCase.execute(GetCurrencyUseCase.Params("USD"))).thenReturn(
                DummyData.dummyUSD

        )
        currencyConvertViewModel.convertCurrency("USD",1.0)
        assert(
            currencyConvertViewModel.state.value == CurrencyConvertState.Update(
                data = 1.2
            )
        )
    }

}