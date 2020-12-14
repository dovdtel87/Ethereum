package com.dmgdavid2109.challenge.feature.data.mapper

import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.feature.data.model.TokensDto
import com.dmgdavid2109.challenge.feature.domain.model.Token
import javax.inject.Inject

class TokenMapper @Inject constructor() : Mapper<TokensDto, Token> {

    override fun map(input: TokensDto): Token {
        return Token(input.name, input.address)
    }
}
