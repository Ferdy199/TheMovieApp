package com.ferdsapp.search.di

import com.ferdsapp.search.data.paging.RemotePagingDataSource
import com.ferdsapp.search.data.source.RemoteDataSource
import javax.inject.Inject

class RemotePagingFactory @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun create(query: String) = RemotePagingDataSource(remoteDataSource = remoteDataSource, query = query)
}