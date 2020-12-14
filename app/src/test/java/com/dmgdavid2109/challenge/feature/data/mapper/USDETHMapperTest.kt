package com.dmgdavid2109.challenge.feature.data.mapper

import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

object USDETHMapperTest : Spek({

    val mapper: USDETHMapper by memoized {
        USDETHMapper()
    }

    val map = mapOf<String, String>(Pair("usd", "123.45"))
    val usdethResponse = USDETHResponse(map)
    val expectedResult = BigDecimal("123.45")

    describe("map") {
        it("then returns a big decimal with the conversion") {
            val result = mapper.map(usdethResponse)
            assertEquals(expectedResult, result)
        }
    }
})
