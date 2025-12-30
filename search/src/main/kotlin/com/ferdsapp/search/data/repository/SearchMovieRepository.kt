package com.ferdsapp.search.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ferdsapp.search.data.model.SearchMovieResult
import com.ferdsapp.search.di.RemotePagingFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMovieRepository @Inject constructor(
    private val pagingFactory: RemotePagingFactory,
): ISearchMovieRepository {
    override suspend fun getSearchMovie(query: String): Flow<PagingData<SearchMovieResult>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 6,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {pagingFactory.create(query)}
        ).flow
    }
}