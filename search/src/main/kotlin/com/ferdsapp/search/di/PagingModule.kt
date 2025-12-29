package com.ferdsapp.search.di

import com.ferdsapp.search.data.paging.RemotePagingDataSource
import com.ferdsapp.search.data.source.RemoteDataSource
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
    ): RemotePagingFactory = RemotePagingFactory(remoteDataSource = remoteDataSource)
}