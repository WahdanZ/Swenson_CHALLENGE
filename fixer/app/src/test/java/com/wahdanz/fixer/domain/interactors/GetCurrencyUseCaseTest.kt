package com.wahdanz.fixer.domain.interactors

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.wahdanz.fixer.domain.repository.FixerRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Test

class GetCurrencyUseCaseTest {
    private val mockFixerRepository: FixerRepository = mock()

    private lateinit var useCase: GetCurrencyUseCase

    @Before
    fun setUp() {
        useCase = GetCurrencyUseCase(mockFixerRepository)
    }

    @Test
    fun `when execute then getFixer`() {
        runBlocking {
            // when
            useCase.execute(GetCurrencyUseCase.Params("USD"))

            // then
            verify(mockFixerRepository).getCurrency("USD")
        }
    }
}