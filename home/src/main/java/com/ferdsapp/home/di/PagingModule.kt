package com.ferdsapp.home.di

import com.ferdsapp.home.data.paging.RemotePagingDataSource
import com.ferdsapp.home.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PagingModule {

    @Provides
    @Singleton
    fun providesRemotePagingFactory(
        remoteDataSource: RemoteDataSource
    ) : RemotePagingFactory = RemotePagingFactory {
        RemotePagingDataSource(remoteDataSource)
    }
}