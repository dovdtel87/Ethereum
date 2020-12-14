package com.dmgdavid2109.challenge.common.data

interface Mapper<I, O> {
    fun map(input: I): O
}
