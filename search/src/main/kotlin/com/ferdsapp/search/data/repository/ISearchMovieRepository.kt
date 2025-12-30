package com.ferdsapp.search.data.repository

import androidx.paging.PagingData
import com.ferdsapp.search.data.model.SearchMovieResult
import kotlinx.coroutines.flow.Flow

interface ISearchMovieRepository {
    suspend fun getSearchMovie(query: String) : Flow<PagingData<SearchMovieResult>>
}