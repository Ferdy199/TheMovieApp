package com.ferdsapp.home.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.paging.RemotePagingDataSource
import com.ferdsapp.home.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Provider

class HomeRepository @Inject constructor(
    private val pagingSourceProvider: Provider<RemotePagingDataSource>
) : IHomeRepository {
    override fun getNowMoviePlaying(): Flow<ApiResponse<PagingData<ResultNowPlayingResponses>>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 4,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { pagingSourceProvider.get() }
        ).flow
            .map<PagingData<ResultNowPlayingResponses>, ApiResponse<PagingData<ResultNowPlayingResponses>>> {
                ApiResponse.Success(it)
            }
            .catch { e -> emit(ApiResponse.Error(e.message ?: "Unknown error")) }
    }
}