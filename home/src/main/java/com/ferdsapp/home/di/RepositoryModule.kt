package com.ferdsapp.home.di

import com.ferdsapp.home.data.repository.HomeRepository
import com.ferdsapp.home.data.repository.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [HomeNetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(homeRepository: HomeRepository): IHomeRepository
}