package com.dmgdavid2109.challenge.common.ui

fun <T : Any> T.toEvent(): Event<T> {
    return Event(this)
}
