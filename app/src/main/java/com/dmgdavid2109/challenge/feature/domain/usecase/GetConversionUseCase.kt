package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.common.network.SchedulerProvider
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import io.reactivex.Single
import java.math.BigDecimal
import javax.inject.Inject

class GetConversionUseCase @Inject constructor(
    private val ethereumRepository: EthereumRepository,
    private val usdEthMapper: Mapper<USDETHResponse, BigDecimal>,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(): Single<BigDecimal> {
        return ethereumRepository.getUsdEthConversion()
            .map(usdEthMapper::map)
            .observeOn(schedulerProvider.ui())
            .subscribeOn(schedulerProvider.io())
    }
}
