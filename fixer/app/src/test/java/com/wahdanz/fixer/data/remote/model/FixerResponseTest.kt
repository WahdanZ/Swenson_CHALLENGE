package com.wahdanz.fixer.data.remote.model

import com.squareup.moshi.Moshi
import org.junit.Test

class FixerResponseTest {

    @Test
    fun `test parse object `() {

        val responseString = """
            {
              "success": true,
              "timestamp": 1620320043,
              "base": "EUR",
              "date": "2021-05-06",
              "rates": {
                "USD": 1.205059,
                "AUD": 1.550513,
                "CAD": 1.469178,
                "PLN": 4.572626,
                "MXN": 24.256231,
                "EGP": 18.877726
              }
            }
        """.trimIndent()
        val moshi = Moshi.Builder()
            .build()

        val issueAdapter = moshi.adapter(FixerResponse::class.java)
        val issue = issueAdapter.fromJson(responseString)
        print(issue)
    }
}
