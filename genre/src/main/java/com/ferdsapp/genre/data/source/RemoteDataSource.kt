package com.ferdsapp.genre.data.source

import com.ferdsapp.core.BuildConfig
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.genre.data.model.GenresMovie
import com.ferdsapp.genre.data.model.ListMovieGenreResponse
import com.ferdsapp.genre.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getMovieListGenre(): Flow<ApiResponse<List<GenresMovie>>> {
        return flow {
            emit(ApiResponse.Loading)
            try {
                val token = BuildConfig.API_TOKEN
                val getMovieListGenreResponse = apiService.getMovieListGenre(
                    authToken = "Bearer $token",
                )
                emit(ApiResponse.Success(getMovieListGenreResponse.genres))
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    suspend fun getListMovieGenre(with_genres: String, page: Int) : ListMovieGenreResponse {
        val token = BuildConfig.API_TOKEN
        val responses = apiService.getListMovieGenre(
            authToken = "Bearer $token",
            with_genres = with_genres,
            page = page
        )
        return responses
    }
}