package com.dmgdavid2109.challenge.feature.domain.repository

import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.data.model.TokensResponse
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import io.reactivex.Single

interface EthereumRepository {
    fun getBalance(address: String): Single<BalanceResponse>

    fun getUsdEthConversion(): Single<USDETHResponse>

    fun getTokenList(): Single<TokensResponse>
}
