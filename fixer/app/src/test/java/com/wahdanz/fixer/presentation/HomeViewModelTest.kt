package com.wahdanz.fixer.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.wahdanz.fixer.core.exception.ErrorHandler
import com.wahdanz.fixer.core.executor.ExecutionThread
import com.wahdanz.fixer.domain.interactors.GetAllCurrenciesUseCase
import com.wahdanz.fixer.dummy.DummyData
import com.wahdanz.fixer.mock.MockedCoroutinesExecutor
import com.wahdanz.fixer.mock.MockedErrorHandler
import com.wahdanz.fixer.presentation.home.HomeViewModel
import com.wahdanz.fixer.presentation.home.FixerHomeState
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class HomeViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private lateinit var errorHandler: ErrorHandler
    private lateinit var executionThread: ExecutionThread
    private var getAllFixersUseCase: GetAllCurrenciesUseCase = mock()
    private var homeViewModel: HomeViewModel = mock()
    @Before
    fun setUp() {
        errorHandler = MockedErrorHandler()
        executionThread = MockedCoroutinesExecutor()
        homeViewModel = HomeViewModel(errorHandler, executionThread, getAllFixersUseCase)
    }
    @Test
    fun `test when getting data return state with data`() = runBlocking {
        whenever(getAllFixersUseCase.execute(GetAllCurrenciesUseCase.Params(listOf("USD","AUD","CAD","PLN","EGP")))).thenReturn(
                DummyData.dummyDomainObject.listCurrencies

        )
        homeViewModel.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
        assert(
            homeViewModel.state.value == FixerHomeState.FixerData(
                data = DummyData.dummyDomainObject.listCurrencies
            )
        )
    }


    @Test
    fun `test return state with error No internet connection when UnknownHostException happen `() =
        runBlocking {
            whenever(getAllFixersUseCase.execute(GetAllCurrenciesUseCase.Params(listOf("USD","AUD","CAD","PLN","EGP")))).thenAnswer { throw UnknownHostException() }
            homeViewModel.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            assert(homeViewModel.state.value == FixerHomeState.Error("No internet connection"))
        }

    @Test
    fun `test return state with error Connection timeout when SocketTimeoutException happen `() =
        runBlocking {
            whenever(getAllFixersUseCase.execute(GetAllCurrenciesUseCase.Params(listOf("USD","AUD","CAD","PLN","EGP")))).thenAnswer { throw SocketTimeoutException() }
            homeViewModel.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            print(homeViewModel.state.value)
            assert(homeViewModel.state.value == FixerHomeState.Error("Connection timeout"))
    }
}