package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.feature.data.mapper.BalanceMapper
import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import com.dmgdavid2109.challenge.common.network.TestSchedulerProvider
import com.dmgdavid2109.challenge.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

object GetBalanceUseCaseSpec : Spek({

    val ethereumRepository: EthereumRepository by mock<EthereumRepository>()

    val useCase: GetBalanceUseCase by memoized {
        GetBalanceUseCase(
            ethereumRepository,
            BalanceMapper(),
            TestSchedulerProvider()
        )
    }

    val balanceResponse = BalanceResponse("123456789012345678")
    val address = "0x001"

    describe("invoke") {
        context("when it success") {
            beforeEachTest {
                every { ethereumRepository.getBalance(address) } returns Single.just(balanceResponse)
            }

            it("retrieves the balance") {
                val expectedResult = BigDecimal("123456789012345678")
                val testObserver = useCase.invoke(address).test()
                testObserver.assertValue(expectedResult)
            }
        }

        context("when it fails") {
            val exception = Exception("An exception")
            beforeEachTest {
                every { ethereumRepository.getBalance(address) } returns Single.error(exception)
            }

            it("returns an error") {
                val testObserver = useCase.invoke(address).test()
                testObserver.assertError(exception)
            }
        }
    }
})
