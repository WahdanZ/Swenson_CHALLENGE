package com.wahdanz.fixer.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.wahdanz.fixer.core.remote.exception.NetworkException
import com.wahdanz.fixer.data.mapper.CurrencyDataMapper
import com.wahdanz.fixer.data.store.FixerCache
import com.wahdanz.fixer.data.store.FixerRemote
import com.wahdanz.fixer.domain.repository.FixerRepository
import com.wahdanz.fixer.dummy.DummyData.dummyDataObject
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.net.SocketTimeoutException

class FixerRepositoryImplTest {
    private val remote: FixerRemote = mock()
    private val cache: FixerCache = mock()
    lateinit var fixerRepository: FixerRepository

    @Before
    fun setUp() {
        fixerRepository = FixerRepositoryImpl(remote = remote, cache = cache, mapper = CurrencyDataMapper())
    }

    @Test
    fun `test get data from remote`() {
        runBlocking {
            whenever(remote.getExchangeRate("","USD,AUD,CAD,PLN,EGP")).thenReturn(dummyDataObject)
            fixerRepository.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            verify(remote, Mockito.times(1)).getExchangeRate("","USD,AUD,CAD,PLN,EGP")
            verify(cache, Mockito.times(0)).getAllCurrencies(date = "2021-05-06")
        }
    }
    @Test
    fun `test get data from cache when no internet connection`() {
        runBlocking {
            whenever(remote.getExchangeRate("","USD,AUD,CAD,PLN,EGP")).thenAnswer { throw SocketTimeoutException() }
            whenever(cache.getAllCurrencies(date = "")).thenReturn(dummyDataObject.listCurrencys)
            fixerRepository.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            verify(remote, Mockito.times(1)).getExchangeRate("","USD,AUD,CAD,PLN,EGP")
            verify(cache, Mockito.times(1)).getAllCurrencies(date = "")
        }
    }

    @Test(expected = SocketTimeoutException::class)
    fun `test rethrow the exception when no internet connection and no data on cache`() {
        runBlocking {
            whenever(remote.getExchangeRate("","USD,AUD,CAD,PLN,EGP")).thenAnswer { throw SocketTimeoutException() }
            whenever(cache.getAllCurrencies(date = "")).thenReturn(listOf())
            fixerRepository.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            verify(remote, Mockito.times(1)).getExchangeRate("","USD,AUD,CAD,PLN,EGP")
            verify(cache, Mockito.times(1)).getAllCurrencies(date = "2021-05-06")
        }
    }
    @Test(expected = NetworkException::class)
    fun `test throw NetworkException when the request not success`() {
        runBlocking {
            whenever(remote.getExchangeRate("","USD,AUD,CAD,PLN,EGP")).thenAnswer { throw NetworkException() }
            fixerRepository.getAllCurrencies(listOf("USD","AUD","CAD","PLN","EGP"))
            verify(remote, Mockito.times(1)).getExchangeRate("","USD,AUD,CAD,PLN,EGP")
            verify(cache, Mockito.times(0)).getAllCurrencies(date = "2021-05-06")
        }
    }

    @Test
    fun `test get Currency form cache`() {
        runBlocking {
            whenever(cache.getCurrency("USD")).thenReturn(dummyDataObject.listCurrencys.find { it.currency == "USD" })
            fixerRepository.getCurrency("USD")
            verify(remote, Mockito.times(0)).getExchangeRate("","USD,AUD,CAD,PLN,EGP")
            verify(cache, Mockito.times(1)).getCurrency("USD")
        }
    }
}