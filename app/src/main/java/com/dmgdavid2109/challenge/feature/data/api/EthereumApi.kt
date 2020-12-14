package com.dmgdavid2109.challenge.feature.data.api

import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface EthereumApi {

   @GET("api")
    fun retrieveBalance(
       @Query("address") address: String,
       @Query("apikey") apikey: String,
       @Query("module") module: String = "account",
       @Query("action") action: String = "balance",
       @Query("tag") tag: String = "latest"
   ): Single<BalanceResponse>
}
