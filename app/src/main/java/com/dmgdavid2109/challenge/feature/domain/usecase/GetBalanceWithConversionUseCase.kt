package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.common.network.SchedulerProvider
import com.dmgdavid2109.challenge.feature.domain.extensions.formattedEthereum
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.math.BigDecimal
import javax.inject.Inject
import kotlin.math.pow

class GetBalanceWithConversionUseCase @Inject constructor(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val getConversionUseCase: GetConversionUseCase,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(address: String): Single<String> {
       return Single.zip(
            getBalanceUseCase.invoke(address),
            getConversionUseCase.invoke(),
            BiFunction<BigDecimal, BigDecimal, String> { balance, conversion ->
                formattedBalance(balance, conversion)
            })
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }

    private fun formattedBalance(balance : BigDecimal, conversion: BigDecimal) : String {
        val eth = balance.formattedEthereum()
        val dollar  = eth.multiply(conversion).setScale(2, BigDecimal.ROUND_FLOOR)
        return "$$dollar ($eth ETH)"
    }
}
