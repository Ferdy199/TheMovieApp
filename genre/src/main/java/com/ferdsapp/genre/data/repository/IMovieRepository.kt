package com.ferdsapp.genre.data.repository

import androidx.paging.PagingData
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.genre.data.model.GenresMovie
import com.ferdsapp.genre.data.model.ResultMovieGenre
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovieListGenre() : Flow<ApiResponse<List<GenresMovie>>>
    fun getListMovieGenre(with_genres: String) : Flow<PagingData<ResultMovieGenre>>
}