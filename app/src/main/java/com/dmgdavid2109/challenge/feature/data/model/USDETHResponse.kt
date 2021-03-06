
package com.dmgdavid2109.challenge.feature.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class USDETHResponse(
   @Json(name = "ethereum") val ethereum : Map<String, String>
)
