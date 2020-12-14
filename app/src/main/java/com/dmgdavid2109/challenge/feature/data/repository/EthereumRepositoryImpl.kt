package com.dmgdavid2109.challenge.feature.data.repository

import com.dmgdavid2109.challenge.common.cache.Cache
import com.dmgdavid2109.challenge.feature.data.api.EthereumApi
import com.dmgdavid2109.challenge.feature.data.api.TokensApi
import com.dmgdavid2109.challenge.feature.data.api.USDETHApi
import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.data.model.TokensResponse
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import io.reactivex.Single
import javax.inject.Inject

class EthereumRepositoryImpl @Inject constructor(
    private val cache: Cache<Any>,
    private val ethereumApi: EthereumApi,
    private val usdethApi: USDETHApi,
    private val tokensApi: TokensApi
) : EthereumRepository {

    override fun getBalance(address: String): Single<BalanceResponse> {
        val cachedBalance = cache.get(address, BalanceResponse::class.java) as? BalanceResponse

        return cachedBalance?.let {
            Single.just(cachedBalance)
        } ?: run {
            ethereumApi.retrieveBalance(
                address,
                "E5QFXD7ZYRH7THQM5PIXB8JD4GY38SEJZ4"
            ).doOnSuccess { balance ->
               cache.set(address, balance)
           }
        }
    }

    override fun getUsdEthConversion(): Single<USDETHResponse> {
        return usdethApi.retrieveChange()
    }

    override fun getTokenList(): Single<TokensResponse> {
        return tokensApi.retrieveAllTokens()
    }
}
