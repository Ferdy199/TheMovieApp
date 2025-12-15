package com.ferdsapp.genre.network

import com.ferdsapp.genre.data.model.ListMovieGenreResponse
import com.ferdsapp.genre.data.model.MovieGenreResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/genre/movie/list")
    suspend fun getMovieListGenre(
        @Header("Authorization")
        authToken: String,

        @Query("language")
        language: String = "id-ID"
    ): MovieGenreResponse

    @GET("3/discover/movie")
    suspend fun getListMovieGenre(
        @Header("Authorization")
        authToken: String,

        @Query("include_adult")
        include_adult: Boolean = true,

        @Query("include_video")
        include_video: Boolean = true,

        @Query("language")
        language: String = "id-ID",

        @Query("page")
        page: Int = 1,

        @Query("region")
        region: String = "id",

        @Query("sort_by")
        sort_by: String = "popularity.desc",

        @Query("with_genres")
        with_genres: String = "12",
    ) : ListMovieGenreResponse
}