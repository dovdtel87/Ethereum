package com.dmgdavid2109.challenge.feature.data.api

import com.dmgdavid2109.challenge.feature.data.model.TokensDto
import com.dmgdavid2109.challenge.feature.data.model.TokensResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TokensApi {

    @GET("getTopTokens")
    fun retrieveAllTokens(
        @Query("limit") limit: String = "100",
        @Query("apiKey") apiKey: String = "freekey"
    ): Single<TokensResponse>
}
