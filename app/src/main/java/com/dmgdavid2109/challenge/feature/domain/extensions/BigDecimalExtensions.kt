package com.dmgdavid2109.challenge.feature.domain.extensions

import java.math.BigDecimal
import kotlin.math.pow

fun BigDecimal.formattedEthereum(scale: Int = 1): BigDecimal = this.divide(BigDecimal(10.0.pow(18))).setScale(scale, BigDecimal.ROUND_FLOOR)
