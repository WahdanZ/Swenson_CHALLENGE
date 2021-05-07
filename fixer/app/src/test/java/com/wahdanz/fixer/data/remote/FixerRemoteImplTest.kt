package com.wahdanz.fixer.data.remote

import com.wahdanz.fixer.data.remote.di.remoteModule
import com.wahdanz.fixer.data.store.FixerRemote
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Test

import org.junit.Before
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

class FixerRemoteImplTest : AutoCloseKoinTest() {
    private lateinit var mockServer: MockWebServer
    private lateinit var FixerRemote: FixerRemote
    @Before
    fun before() {
        mockServer = MockWebServer()
        mockServer.start()

        startKoin {
            modules(listOf(remoteModule))
        }
    }
    @Test
    fun `test success response`() = runBlocking {

        val mockedResponse = MockResponse().apply {
            setResponseCode(200)
        }
        mockServer.enqueue(mockedResponse)
        mockServer.takeRequest()
    }
}