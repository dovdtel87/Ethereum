package com.dmgdavid2109.challenge.feature.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmgdavid2109.challenge.R
import com.dmgdavid2109.challenge.common.ui.Event
import com.dmgdavid2109.challenge.common.ui.ViewStateLiveData
import com.dmgdavid2109.challenge.common.ui.toEvent
import com.dmgdavid2109.challenge.common.ui.BaseViewModel
import com.dmgdavid2109.challenge.feature.domain.usecase.GetBalanceWithConversionUseCase
import com.uber.autodispose.autoDispose
import io.reactivex.functions.Consumer
import javax.inject.Inject

class BalanceViewModel @Inject constructor(
    private val getBalanceWithConversionUseCase: GetBalanceWithConversionUseCase
) : BaseViewModel(),
    BalanceViewModelInput {

    private val _viewState = ViewStateLiveData(BalanceViewState())
    val viewState: LiveData<BalanceViewState>
        get() = _viewState

    private val _navigateToTokens = MutableLiveData<Event<Boolean>>()
    val navigateToTokens: LiveData<Event<Boolean>>
        get() = _navigateToTokens

    init {
        loadBalanceWithConversion()
    }

    private fun loadBalanceWithConversion() {
        _viewState.value?.address?.let { it ->
            getBalanceWithConversionUseCase
                .invoke(it)
                .doOnSubscribe{
                    showStartLoading()
                }
                .autoDispose(this)
                .subscribe(Consumer {
                    showBalance(it)
                }, this)
        }

    }

    override fun onButtonTapped() {
        navigateToERC20Tokens()
    }

    private fun navigateToERC20Tokens() {
        _navigateToTokens.value = true.toEvent()
    }

    private fun showStartLoading() {
        _viewState.updateCurrentState {
            copy(isLoading = true)
        }
    }

    private fun showBalance(balance: String) {
        _viewState.updateCurrentState {
            copy(
                isLoading = false,
                errorMessage = null,
                balance = balance
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

    override fun retry() {
        loadBalanceWithConversion()
    }
}
