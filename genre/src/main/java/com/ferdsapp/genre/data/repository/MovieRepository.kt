package com.ferdsapp.genre.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.genre.data.model.GenresMovie
import com.ferdsapp.genre.data.model.ResultMovieGenre
import com.ferdsapp.genre.data.paging.RemotePagingDataSource
import com.ferdsapp.genre.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IMovieRepository {
    override fun getMovieListGenre(): Flow<ApiResponse<List<GenresMovie>>> {
        return flow {
            try {
                remoteDataSource.getMovieListGenre().collect { movieGenre ->
                    when(movieGenre){
                        is ApiResponse.Empty -> emit((ApiResponse.Empty))
                        is ApiResponse.Error -> emit(ApiResponse.Error(movieGenre.errorMessage))
                        is ApiResponse.Loading -> emit(ApiResponse.Loading)
                        is ApiResponse.Success -> {
                            emit(ApiResponse.Success(movieGenre.data))
                        }
                    }
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.message.toString()))
            }
        }
    }

    override fun getListMovieGenre(with_genres: String): Flow<PagingData<ResultMovieGenre>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RemotePagingDataSource(remoteDataSource, with_genres) }
        ).flow
    }
}