package com.dmgdavid2109.challenge.feature.data.api

import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface USDETHApi {

    @GET("api/v3/simple/price?ids=ethereum&vs_currencies=usd")
    fun retrieveChange(
       /* @Query("address") address: String,
        @Query("apikey") apikey: String,
        @Query("module") module: String = "account",
        @Query("action") action: String = "balance",
        @Query("tag") tag: String = "latest"*/
    ): Single<USDETHResponse>
}
