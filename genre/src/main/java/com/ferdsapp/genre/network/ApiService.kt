package com.ferdsapp.genre.network

import com.ferdsapp.genre.data.model.MovieGenreResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/genre/movie/list")
    fun getMovieListGenre(
        @Header("Authorization")
        authToken: String,

        @Query("language")
        language: String = "id-ID"
    ): MovieGenreResponse
}