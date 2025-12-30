package com.ferdsapp.home.data.source

import com.ferdsapp.core.BuildConfig
import com.ferdsapp.home.data.model.now_playing.NowPlayingMovieResponses
import com.ferdsapp.home.network.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
   private val apiService: ApiService
) {
    suspend fun getNowPlayingMovie(page: Int? = 1) : NowPlayingMovieResponses {
        return try {
            val token = BuildConfig.API_TOKEN
            val responses = apiService.getNowPlayingMovie(
                authToken = "Bearer $token",
                page = page ?: 1
            )
            responses
        }catch (e: Exception){
            throw e
        }
    }
}