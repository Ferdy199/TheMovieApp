package com.ferdsapp.detail.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResultResponse
import com.ferdsapp.detail.data.source.RemoteDataSource
import javax.inject.Inject

class RemotePagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val movieId: Int
): PagingSource<Int, MovieDetailReviewResultResponse>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDetailReviewResultResponse>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1) ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDetailReviewResultResponse> {
        return try {
            val page = params.key ?: 1
            val responses = remoteDataSource.getDetailReview(movieId)
            LoadResult.Page(
                data = responses.results ?: listOf(),
                prevKey = if (page == 1) null else page -1,
                nextKey = if (page < responses.total_pages!!) page + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }

    }

}