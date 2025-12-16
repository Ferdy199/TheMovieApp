package com.ferdsapp.detail.data.repository

import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IDetailRepository {
    override suspend fun getDetailRepository(movieId: Int): Flow<ApiResponse<MovieDetailsResponse>> {
       return flow {
           try {
               remoteDataSource.getDetailMovie(movieId = movieId).collect { movieDetailResponse ->
                   when(movieDetailResponse){
                       is ApiResponse.Empty -> emit(ApiResponse.Empty)
                       is ApiResponse.Error -> emit(ApiResponse.Error(movieDetailResponse.errorMessage))
                       is ApiResponse.Loading -> emit(ApiResponse.Loading)
                       is ApiResponse.Success -> {
                           val data = movieDetailResponse.data
                           emit(ApiResponse.Success(data = data))
                       }
                   }

               }
           }catch (e: Exception){
               emit(ApiResponse.Error(e.message.toString()))
           }

       }
    }
}