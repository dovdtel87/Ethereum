package com.dmgdavid2109.challenge.feature.data.mapper

import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import java.math.BigDecimal
import javax.inject.Inject

class BalanceMapper @Inject constructor() : Mapper<BalanceResponse, BigDecimal> {

    override fun map(input: BalanceResponse): BigDecimal {
        return BigDecimal(input.result)
    }
}
