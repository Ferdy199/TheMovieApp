package com.ferdsapp.detail.di

import com.ferdsapp.detail.data.paging.RemotePagingDataSource
import com.ferdsapp.detail.data.source.RemoteDataSource
import javax.inject.Inject

class RemotePagingFactory @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun create(movieId: Int) = RemotePagingDataSource(remoteDataSource, movieId)
}