package com.ferdsapp.detail.di

import com.ferdsapp.detail.data.repository.DetailRepository
import com.ferdsapp.detail.data.repository.IDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DetailNetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class DetailRepositoryModule{
    @Binds
    abstract fun provideRepository(detailRepository: DetailRepository) : IDetailRepository
}