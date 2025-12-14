package com.ferdsapp.home.network

import com.ferdsapp.home.data.model.now_playing.NowPlayingMovieResponses
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {
    fun getNowPlayingMovie(
        @Header("Authorization")
        authToken: String,

        @Query("language") language: String = "id-ID",

        @Query("page") page: Int = 1,

        @Query("region") region: String = "id"
    ) : NowPlayingMovieResponses
}