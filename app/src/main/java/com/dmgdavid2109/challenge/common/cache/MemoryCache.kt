package com.dmgdavid2109.challenge.common.cache

import android.util.LruCache
import javax.inject.Inject

class MemoryCache<T : Any> @Inject constructor(private val cache: LruCache<String, T>) : Cache<T> {

    override fun get(key: String, type: Class<out Any>): T? {
        return cache[generateKey(key, type)]
    }

    override fun set(key: String, value: T) {
        cache.put(generateKey(key, value::class.java), value)
    }

    override fun clear() {
        cache.evictAll()
    }

    private fun generateKey(id: String, type: Class<out Any>): String {
        return type.simpleName + "-" + id
    }
}
