package com.ferdsapp.detail.data.repository

import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IDetailRepository {
    suspend fun getDetailRepository(movieId: Int, page: Int?) : Flow<MovieDetailsResponse>
}