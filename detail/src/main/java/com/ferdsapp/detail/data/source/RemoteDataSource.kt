package com.ferdsapp.detail.data.source

import android.util.Log
import com.ferdsapp.core.BuildConfig
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getDetailMovie(
        movieId: Int,
    ) : Flow<ApiResponse<MovieDetailsResponse>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val token = BuildConfig.API_TOKEN

//                get Detail first
                val detailResponse = apiService.getMovieDetail(
                    authToken = "Bearer $token",
                    movie_id = movieId,
                )

//                get Detail Trailer
                val detailTrailer = apiService.detailMovieTrailer(
                    authToken = "Bearer $token",
                    movie_id = movieId,
                )

                val updatedResponse = detailResponse
                    .copy(
                        movieTrailer = if (!detailTrailer.results.isNullOrEmpty()) detailTrailer else null,
                    )

                Log.d("remoteDataSource", "emit Success")
                emit(ApiResponse.Success(updatedResponse))

            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    suspend fun getDetailReview(movieId: Int): MovieDetailReviewResponse {
       return try {
           val token = BuildConfig.API_TOKEN
           val responses = apiService.getDetailMovieReview(
               authToken = "Bearer $token",
               movie_id = movieId,
           )
            responses
       }catch (e: Exception){
           throw e
       }
    }
}