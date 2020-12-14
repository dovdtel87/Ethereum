package com.dmgdavid2109.challenge.di

import com.dmgdavid2109.challenge.common.network.AppSchedulerProvider
import com.dmgdavid2109.challenge.common.network.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ApiConfigsModule {

    @Provides
    @Singleton
    fun providesSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}
