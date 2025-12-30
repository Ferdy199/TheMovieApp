package com.ferdsapp.search.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.search.data.model.SearchMovieResult
import com.ferdsapp.search.data.source.RemoteDataSource
import javax.inject.Inject

class RemotePagingDataSource @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val query: String
): PagingSource<Int, SearchMovieResult>(){
    override fun getRefreshKey(state: PagingState<Int, SearchMovieResult>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SearchMovieResult> {
       return try {
           val page = params.key ?: 1
           val responses = remoteDataSource.getSearchMovie(query = query, page)
           LoadResult.Page(
               data = responses.results ?: listOf(),
               prevKey = if (page == 1) null else page - 1,
               nextKey = if (page < responses.total_pages) page + 1 else null
           )
       }catch (e: Exception){
           LoadResult.Error(e)
       }
    }

}