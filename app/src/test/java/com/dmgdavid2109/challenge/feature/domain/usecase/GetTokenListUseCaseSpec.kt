package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.common.data.ListMapper
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import com.dmgdavid2109.challenge.common.network.TestSchedulerProvider
import com.dmgdavid2109.challenge.feature.data.mapper.TokenMapper
import com.dmgdavid2109.challenge.feature.data.model.TokensDto
import com.dmgdavid2109.challenge.feature.data.model.TokensResponse
import com.dmgdavid2109.challenge.feature.domain.model.Token
import com.dmgdavid2109.challenge.helpers.mock
import io.mockk.every
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object GetTokenListUseCaseSpec : Spek({

    val ethereumRepository: EthereumRepository by mock<EthereumRepository>()
    val tokenMapper: ListMapper<TokensDto, Token> = ListMapper(TokenMapper())

    val useCase: GetTokenListUseCase by memoized {
        GetTokenListUseCase(
            ethereumRepository,
            tokenMapper,
            TestSchedulerProvider()
        )
    }

    val tokenList = listOf(
        TokensDto("Token1","0x001"),
        TokensDto("Token2","0x002"),
        TokensDto("Token3","0x003")
    )

    val listExpected = listOf(
        Token("Token1","0x001"),
        Token("Token2","0x002"),
        Token("Token3","0x003")
    )

    val tokensResponse = TokensResponse(tokenList)

    describe("invoke") {
        context("when it success") {
            beforeEachTest {
                every { ethereumRepository.getTokenList() } returns Single.just(tokensResponse)
            }

            it("retrieves a list of tokens") {
                val testObserver = useCase.invoke().test()
                testObserver.assertValue(listExpected)
            }
        }

        context("when it fails") {
            val exception = Exception("An exception")
            beforeEachTest {
                every { ethereumRepository.getTokenList() } returns Single.error(exception)
            }

            it("returns an error") {
                val testObserver = useCase.invoke().test()
                testObserver.assertError(exception)
            }
        }
    }
})
