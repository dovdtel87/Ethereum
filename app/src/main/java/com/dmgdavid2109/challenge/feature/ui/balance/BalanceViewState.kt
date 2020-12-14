package com.dmgdavid2109.challenge.feature.ui.balance

import com.dmgdavid2109.challenge.common.ui.LceViewState

data class BalanceViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val address: String = "0x082d3e0f04664b65127876e9A05e2183451c792a",
    val balance: String = ""
) : LceViewState
