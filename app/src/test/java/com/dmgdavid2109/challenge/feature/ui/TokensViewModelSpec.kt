package com.dmgdavid2109.challenge.feature.ui

import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.feature.domain.model.Token
import com.dmgdavid2109.challenge.feature.domain.usecase.GetBalanceUseCase
import com.dmgdavid2109.challenge.feature.domain.usecase.GetTokenListUseCase
import com.dmgdavid2109.challenge.feature.ui.tokens.TokensViewModel
import com.dmgdavid2109.challenge.feature.ui.tokens.TokensViewState
import com.dmgdavid2109.challenge.helpers.getValueTest
import com.dmgdavid2109.challenge.helpers.mock
import com.dmgdavid2109.challenge.helpers.withInstantTaskExecutor
import io.mockk.every
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal
import java.math.BigInteger

class TokensViewModelSpec : Spek({
    withInstantTaskExecutor()

    val getTokenListUseCase: GetTokenListUseCase by mock<GetTokenListUseCase>()
    val getBalanceUseCase: GetBalanceUseCase by mock<GetBalanceUseCase>()

    val viewModel: TokensViewModel by memoized {
        TokensViewModel(
            getTokenListUseCase,
            getBalanceUseCase
        )
    }

    val token1 = Token ("Token 1","0x001")
    val token2 = Token ("Token 2","0x002")
    val token3 = Token ("Token 3","0x003")
    val list = listOf(
        token1,
        token2,
        token3
    )

    describe("init") {
        context("when list successfully retrieved") {
            val expectedViewState =
                TokensViewState(
                    false,
                    null,
                    list,
                    BigDecimal(BigInteger.ZERO)
                )
            beforeEachTest {
                every { getTokenListUseCase.invoke() } returns Single.just(list)
            }
            it("retrieves the token list") {
                assertEquals(expectedViewState, viewModel.viewState.getValueTest())
            }
        }

        context("when there is an error") {
            val expectedViewStateError =
                TokensViewState(
                    false,
                    R.string.generic_error,
                    emptyList(),
                    BigDecimal.ZERO
                )
            beforeEachTest {
                every { getTokenListUseCase.invoke() } returns Single.error(Exception())
            }
            it("displays an error") {
                assertEquals(expectedViewStateError, viewModel.viewState.getValueTest())
            }
        }
    }
})
