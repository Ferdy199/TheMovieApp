package com.ferdsapp.genre.data.repository

import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.genre.data.model.GenresMovie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovieListGenre() : Flow<ApiResponse<List<GenresMovie>>>
}