package com.dmgdavid2109.challenge.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BalanceResponse(
   @Json(name = "result") val result : String
)
