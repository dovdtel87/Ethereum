package com.dmgdavid2109.challenge.feature.data.mapper

import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import java.math.BigDecimal
import javax.inject.Inject

class USDETHMapper @Inject constructor() : Mapper<USDETHResponse, BigDecimal> {
    override fun map(input: USDETHResponse) : BigDecimal {
        return BigDecimal(input.ethereum["usd"])
    }
}
