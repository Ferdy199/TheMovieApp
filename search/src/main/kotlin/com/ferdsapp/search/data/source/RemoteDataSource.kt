package com.ferdsapp.search.data.source

import com.ferdsapp.core.BuildConfig
import com.ferdsapp.search.data.model.SearchMovieResponses
import com.ferdsapp.search.network.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getSearchMovie(query: String): SearchMovieResponses {
        return try {
            val token = BuildConfig.API_TOKEN
            val responses = apiService.getMovieSearch(
                authToken = "Bearer $token",
                query = query
            )
            responses
        }catch (e: Exception){
            throw e
        }
    }
}