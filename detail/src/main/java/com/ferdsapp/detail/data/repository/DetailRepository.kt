package com.ferdsapp.detail.data.repository

import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IDetailRepository {
    override suspend fun getDetailRepository(movieId: Int, page: Int?): Flow<MovieDetailsResponse> {
        TODO("Not yet implemented")
    }
}