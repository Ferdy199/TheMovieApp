package com.ferdsapp.detail.data.source

import com.ferdsapp.core.BuildConfig
import com.ferdsapp.core.utils.ApiResponse
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

//                get Detail Review
                val detailReview = apiService.getDetailMovieReview(
                    authToken = "Bearer $token",
                    movie_id = movieId,
                )

                if (!detailTrailer.results.isNullOrEmpty()){
                    detailResponse.copy(
                        movieTrailer = detailTrailer
                    )
                }

                if (!detailReview.results.isNullOrEmpty()){
                    detailResponse.copy(
                        movieReview = detailReview
                    )
                }

                emit(ApiResponse.Success(detailResponse))

            }catch (e: Exception){

            }
        }
    }
}