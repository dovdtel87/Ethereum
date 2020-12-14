package com.dmgdavid2109.challenge.feature.domain.model

data class Token(
    val name : String,
    val address : String
) {
    override fun toString(): String {
        return name
    }
}
