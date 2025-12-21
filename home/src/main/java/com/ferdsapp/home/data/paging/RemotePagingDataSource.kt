package com.ferdsapp.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.source.RemoteDataSource
import javax.inject.Inject

class RemotePagingDataSource @Inject constructor (
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, ResultNowPlayingResponses>() {
    override fun getRefreshKey(state: PagingState<Int, ResultNowPlayingResponses>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultNowPlayingResponses> {
        return try {
            val page = params.key ?: 1
            val responses = remoteDataSource.getNowPlayingMovie(page)

            LoadResult.Page(
                data = responses.results,
                prevKey = if (page == 1) null else page -1,
                nextKey = if (page < responses.total_pages) page + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}