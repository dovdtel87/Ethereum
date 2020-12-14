package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.common.network.SchedulerProvider
import io.reactivex.Single
import java.math.BigDecimal
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(
    private val ethereumRepository: EthereumRepository,
    private val balanceMapper: Mapper<BalanceResponse, BigDecimal>,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(address: String): Single<BigDecimal> {
        return ethereumRepository.getBalance(address)
            .map(balanceMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
