package com.ferdsapp.detail.data.repository

import androidx.paging.PagingData
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResultResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import kotlinx.coroutines.flow.Flow

interface IDetailRepository {
    suspend fun getDetailRepository(movieId: Int) : Flow<ApiResponse<MovieDetailsResponse>>
    suspend fun getMovieReviewResponse(movieId: Int) : Flow<PagingData<MovieDetailReviewResultResponse>>
}