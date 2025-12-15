package com.ferdsapp.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.paging.RemotePagingDataSource
import com.ferdsapp.home.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IHomeRepository {
    override fun getNowMoviePlaying(): Flow<PagingData<ResultNowPlayingResponses>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 6,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RemotePagingDataSource(remoteDataSource) }
        ).flow
    }
}