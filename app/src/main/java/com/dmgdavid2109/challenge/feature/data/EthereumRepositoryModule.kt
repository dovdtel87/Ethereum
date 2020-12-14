package com.dmgdavid2109.challenge.feature.data

import android.util.LruCache
import com.dmgdavid2109.challenge.common.cache.Cache
import com.dmgdavid2109.challenge.common.cache.MemoryCache
import com.dmgdavid2109.challenge.common.data.Mapper
import com.dmgdavid2109.challenge.feature.data.api.EthereumApi
import com.dmgdavid2109.challenge.feature.data.api.TokensApi
import com.dmgdavid2109.challenge.feature.data.api.USDETHApi
import com.dmgdavid2109.challenge.feature.data.mapper.BalanceMapper
import com.dmgdavid2109.challenge.feature.data.mapper.TokenMapper
import com.dmgdavid2109.challenge.feature.data.mapper.USDETHMapper
import com.dmgdavid2109.challenge.feature.data.model.BalanceResponse
import com.dmgdavid2109.challenge.feature.data.model.TokensDto
import com.dmgdavid2109.challenge.feature.data.model.USDETHResponse
import com.dmgdavid2109.challenge.feature.data.repository.EthereumRepositoryImpl
import com.dmgdavid2109.challenge.feature.domain.model.Token
import com.dmgdavid2109.challenge.feature.domain.repository.EthereumRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit
import java.math.BigDecimal
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
abstract class EthereumRepositoryModule {

    @Binds
    @ActivityScoped
    internal abstract fun ethereumRepository(repository: EthereumRepositoryImpl): EthereumRepository

    @Binds
    internal abstract fun mapper(mapper: BalanceMapper): Mapper<BalanceResponse, BigDecimal>

    @Binds
    internal abstract fun conversionMapper(mapper: USDETHMapper): Mapper<USDETHResponse, BigDecimal>

    @Binds
    internal abstract fun tokensMapper(mapper: TokenMapper): Mapper<TokensDto, Token>

    @Binds
    internal abstract fun cache(mapper: MemoryCache<Any>): Cache<Any>

    companion object {
        @Provides
        @ActivityScoped
        internal fun provideApi(
            @Named("retrofit_api") retrofit: Retrofit
        ): EthereumApi {
            return retrofit.create(EthereumApi::class.java)
        }

        @Provides
        @ActivityScoped
        internal fun provideUSDApi(
            @Named("retrofit_change") retrofit: Retrofit
        ): USDETHApi {
            return retrofit.create(USDETHApi::class.java)
        }

        @Provides
        @ActivityScoped
        internal fun provideTokensApi(
            @Named("retrofit_token") retrofit: Retrofit
        ): TokensApi {
            return retrofit.create(TokensApi::class.java)
        }


        @Provides
        @ActivityScoped
        internal fun cache() : LruCache<String, Any> {
            val MAX_MEMORY_SIZE = 1024 * 1024 * 5
            return LruCache<String, Any>(MAX_MEMORY_SIZE)
        }
    }
}
