package com.dmgdavid2109.challenge.common.cache

interface Cache<T> {
    companion object {
        const val DEFAULT_KEY = ""
    }

    fun get(key: String = DEFAULT_KEY, type: Class<out Any>): T?
    fun set(key: String = DEFAULT_KEY, value: T)
    fun clear()
}
