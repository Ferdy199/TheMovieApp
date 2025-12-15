package com.ferdsapp.home.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ferdsapp.core.BuildConfig
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.home.data.model.now_playing.NowPlayingMovieResponses
import com.ferdsapp.home.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
   private val apiService: ApiService
) {
//    fun getNowPlayingMovie(page : Int = 1) : Flow<ApiResponse<List<ResultNowPlayingResponses>>> {
//        return flow {
//            try {
//                val token = BuildConfig.API_TOKEN
//                val responses = apiService.getNowPlayingMovie(
//                    authToken = "Bearer $token",
//                    page = page
//                )
//                val dataResponse = responses.results
//
//                if (dataResponse.isNotEmpty()){
//                    emit(ApiResponse.Success(dataResponse))
//                }else{
//                    emit(ApiResponse.Empty)
//                }
//
//            }catch (e: Exception){
//                emit(ApiResponse.Error(errorMessage = e.message.toString()))
//            }
//        }
//    }

    fun getNowPlayingMovie(page: Int? = 1) : NowPlayingMovieResponses {
        val token = BuildConfig.API_TOKEN
        val responses = apiService.getNowPlayingMovie(
            authToken = "Bearer $token",
            page = page ?: 1
        )
        return responses
    }
}