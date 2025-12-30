package com.ferdsapp.search.network

import com.ferdsapp.search.data.model.SearchMovieResponses
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    @GET("3/search/movie")
    suspend fun getMovieSearch(
        @Header("Authorization")
        authToken: String,

        @Query("query") query: String = "",

        @Query("language") language: String = "en-US",

        @Query("page") page: Int = 1,

        @Query("region") region: String = "us"
    ) : SearchMovieResponses
}