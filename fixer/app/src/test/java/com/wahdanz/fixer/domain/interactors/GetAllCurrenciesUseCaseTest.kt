package com.wahdanz.fixer.domain.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wahdanz.fixer.domain.repository.FixerRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class GetAllCurrenciesUseCaseTest {
    private val mockFixerRepository: FixerRepository = mock()

    private lateinit var useCase: GetAllCurrenciesUseCase
    @Before
    fun setUp() {
        useCase = GetAllCurrenciesUseCase(mockFixerRepository)
    }
    @Test
    fun `when execute then getAllFixers`() {
        runBlocking {
            // when
            useCase.execute(GetAllCurrenciesUseCase.Params(listOf("USD")))

            // then
            verify(mockFixerRepository).getAllCurrencies(listOf("USD"))
        }
    }
}