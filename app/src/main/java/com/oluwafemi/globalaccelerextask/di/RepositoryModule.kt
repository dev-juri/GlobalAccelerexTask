package com.oluwafemi.globalaccelerextask.di

import com.oluwafemi.globalaccelerextask.network.Service
import com.oluwafemi.globalaccelerextask.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepositoryImpl(service: Service): RepositoryImpl {
        return RepositoryImpl(service)
    }
}