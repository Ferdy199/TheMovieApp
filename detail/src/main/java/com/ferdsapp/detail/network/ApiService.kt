package com.ferdsapp.detail.network

import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailTrailersResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(
        @Header("Authorization")
        authToken: String,

        @Path("movie_id")
        movie_id: Int,

        @Query("language")
        language: String = "en-US"
    ): MovieDetailsResponse

    @GET("3/movie/{movie_id}/reviews")
    suspend fun getDetailMovieReview(
        @Header("Authorization")
        authToken: String,

        @Path("movie_id")
        movie_id: Int,

        @Query("language")
        language: String = "en-US",

        @Query("page")
        page: Int = 1
    ): MovieDetailReviewResponse

    @GET("3/movie/{movie_id}/videos")
    suspend fun detailMovieTrailer(
    @Header("Authorization")
    authToken: String,

    @Path("movie_id")
    movie_id: Int,

    @Query("language")
    language: String = "en-US",

    ): MovieDetailTrailersResponse
}