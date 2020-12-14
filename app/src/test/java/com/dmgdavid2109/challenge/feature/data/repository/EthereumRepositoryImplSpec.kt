package com.dmgdavid2109.challenge.feature.data.repository

import com.dmgdavid2109.challenge.common.cache.MemoryCache
import com.dmgdavid2109.challenge.feature.data.api.EthereumApi
import com.dmgdavid2109.challenge.feature.data.api.TokensApi
import com.dmgdavid2109.challenge.feature.data.api.USDETHApi
import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import com.dmgdavid2109.challenge.helpers.mock
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class EthereumRepositoryImplSpec : Spek({

    val apiEthereumApi: EthereumApi by mock<EthereumApi>()
    val apiUSDETHApi: USDETHApi by mock<USDETHApi>()
    val apiTokensApi: TokensApi by mock<TokensApi>()
    val cache: MemoryCache<Any> = mockk(relaxed = true)

    val repository: EthereumRepositoryImpl by memoized {
        EthereumRepositoryImpl(cache, apiEthereumApi, apiUSDETHApi, apiTokensApi)
    }

    val balanceResponse = BalanceResponse("123456789012345678")
    val map = mapOf<String, String>(Pair("result", "123.45"))
    val uSDETHResponse = USDETHResponse(map)

    describe("getBalance") {
        context("when there is no value in the cache ") {
            beforeEachTest {
                every {
                    apiEthereumApi.retrieveBalance("0x001", "aKey")
                } returns Single.just(balanceResponse)
            }

            it("return the balance") {
                val testObserver = repository.getBalance("0x001").test()
                testObserver.assertValue(balanceResponse)
            }
        }
        context("when there is value in the cache ") {
            beforeEachTest {
                every { cache.get("0x001", BalanceResponse::class.java) } returns BalanceResponse("223344")
            }

            it("return the balance") {
                val testObserver = repository.getBalance("0x001").test()
                testObserver.assertValue(BalanceResponse("223344"))
            }
        }
    }

    describe("getUsdEthConversion") {
        beforeEachTest {
            every {
                apiUSDETHApi.retrieveChange()
            } returns Single.just(uSDETHResponse)
        }

        it("return change") {
            val testObserver =  repository.getUsdEthConversion().test()
            testObserver.assertValue(uSDETHResponse)
        }
    }
})
