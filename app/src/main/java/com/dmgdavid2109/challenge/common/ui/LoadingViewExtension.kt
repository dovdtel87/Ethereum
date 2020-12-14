package com.dmgdavid2109.challenge.common.ui

import com.dmgdavid2109.challenge.common.ui.customviews.LoadingView

fun LoadingView.setViewModelInputs(viewModelInputs: LceViewModelInputs) {
    setRetryListener(viewModelInputs::retry)
}

fun LoadingView.setViewState(viewState: LceViewState) {
    when {
        viewState.isLoading -> status(LoadingView.VisibilityState.LOADING)
        viewState.errorMessage != null -> status(LoadingView.VisibilityState.OFFLINE)
        else -> status(LoadingView.VisibilityState.GONE)
    }
}
