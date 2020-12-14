package com.dmgdavid2109.challenge.feature.ui.tokens

import androidx.lifecycle.LiveData
import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.common.ui.ViewStateLiveData
import com.dmgdavid2109.challenge.common.ui.BaseViewModel
import com.dmgdavid2109.challenge.feature.domain.extensions.formattedEthereum
import com.dmgdavid2109.challenge.feature.domain.model.Token
import com.dmgdavid2109.challenge.feature.domain.usecase.GetBalanceUseCase
import com.dmgdavid2109.challenge.feature.domain.usecase.GetTokenListUseCase
import com.uber.autodispose.autoDispose
import io.reactivex.functions.Consumer
import java.math.BigDecimal
import javax.inject.Inject

class TokensViewModel @Inject constructor(
    private val getTokenListUseCase: GetTokenListUseCase,
    private val getBalanceUseCase: GetBalanceUseCase
) : BaseViewModel(),
    TokensViewModelInput {

    private val _viewState = ViewStateLiveData(TokensViewState())

    val viewState: LiveData<TokensViewState>
        get() = _viewState

    init {
        loadTokens()
    }

    private fun loadTokens() {
        getTokenListUseCase
            .invoke()
            .doOnSubscribe{
                showStartLoading()
            }
            .autoDispose(this)
            .subscribe(Consumer {
                showTokens(it)
            }, this)
    }

    override fun retry() {
        loadTokens()
    }

    override fun getBalance(address: String) {
        getBalanceUseCase.invoke(address)
            .autoDispose(this)
            .subscribe(Consumer {
                showBalance(it)
            }, this)
    }

    private fun showBalance(balance: BigDecimal) {
        _viewState.updateCurrentState {
            copy(balance = balance.formattedEthereum(5))
        }
    }

    private fun showStartLoading() {
        _viewState.updateCurrentState {
            copy(isLoading = true)
        }
    }

    private fun showTokens(tokens: List<Token>) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = null,
                tokens = tokens
            )
        }
    }

    override fun accept(t: Throwable?) {
        super.accept(t)
        showError()
    }

    private fun showError() {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = R.string.generic_error
            )
        }
    }
}
