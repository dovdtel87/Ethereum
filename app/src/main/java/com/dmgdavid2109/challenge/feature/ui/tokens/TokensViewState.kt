package com.dmgdavid2109.challenge.feature.ui.tokens

import com.dmgdavid2109.challenge.common.ui.LceViewState
import com.dmgdavid2109.challenge.feature.domain.model.Token
import java.math.BigDecimal

data class TokensViewState(
    override val isLoading: Boolean = true,
    override val errorMessage: Int? = null,
    val tokens : List<Token> = emptyList(),
    val balance: BigDecimal = BigDecimal.ZERO
) : LceViewState
