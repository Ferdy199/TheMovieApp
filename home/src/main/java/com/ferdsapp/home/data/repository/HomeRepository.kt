package com.ferdsapp.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.paging.RemotePagingDataSource
import com.ferdsapp.home.data.source.RemoteDataSource
import com.ferdsapp.home.di.RemotePagingFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val factory: RemotePagingFactory
) : IHomeRepository {
    override fun getNowMoviePlaying(): Flow<PagingData<ResultNowPlayingResponses>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 4,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { factory.create() }
        ).flow
    }
}