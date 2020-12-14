package com.dmgdavid2109.challenge.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TokensDto(
    @Json(name = "name") val name : String,
    @Json(name = "address") val address : String
)
