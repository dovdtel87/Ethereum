package com.dmgdavid2109.challenge.feature.data.mapper

import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

object BalanceMapperTest : Spek({

    val mapper: BalanceMapper by memoized {
        BalanceMapper()
    }

    val expectedResult = BigDecimal("123456789012345678")

    val balanceResponse = BalanceResponse("123456789012345678")

    describe("map") {
        it("then returns a big decimal") {
            val result = mapper.map(balanceResponse)
            assertEquals(expectedResult, result)
        }
    }
})
