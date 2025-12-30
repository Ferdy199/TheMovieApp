package com.ferdsapp.genre.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.genre.data.model.ResultMovieGenre
import com.ferdsapp.genre.data.source.RemoteDataSource


class RemotePagingDataSource (
    private val remoteDataSource: RemoteDataSource,
    private val with_genres: String
) : PagingSource<Int, ResultMovieGenre>() {
    override fun getRefreshKey(state: PagingState<Int, ResultMovieGenre>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultMovieGenre> {
        return try {
            val page = params.key ?: 1
            val responses = remoteDataSource.getListMovieGenre(with_genres, page)

            LoadResult.Page(
                data = responses.results ?: listOf(),
                prevKey = if (page == 1) null else page -1,
                nextKey = if (page < responses.total_pages) page + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }
}