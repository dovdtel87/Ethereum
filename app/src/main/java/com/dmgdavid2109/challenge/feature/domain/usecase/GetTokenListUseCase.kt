package com.dmgdavid2109.challenge.feature.domain.usecase

import com.dmgdavid2109.challenge.common.data.ListMapper
import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.common.network.SchedulerProvider
import com.dmgdavid2109.challenge.feature.data.model.TokensDto
import com.dmgdavid2109.challenge.feature.data.model.TokensResponse
import com.dmgdavid2109.challenge.feature.domain.model.Token
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import io.reactivex.Single
import javax.inject.Inject

class GetTokenListUseCase @Inject constructor(
    private val ethereumRepository: EthereumRepository,
    private val tokensMapper: ListMapper<TokensDto, Token>,
    private val schedulerProvider: SchedulerProvider
) {
    operator fun invoke(): Single<List<Token>> {
        return ethereumRepository.getTokenList().map {
            it.tokens
        }.map(tokensMapper::map)
        .observeOn(schedulerProvider.ui())
        .subscribeOn(schedulerProvider.io())
    }
}
