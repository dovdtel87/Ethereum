package com.dmgdavid2109.challenge.feature.ui.tokens

import com.dmgdavid2109.challenge.common.ui.LceViewModelInputs

interface TokensViewModelInput : LceViewModelInputs {
    fun getBalance(address: String)
}
