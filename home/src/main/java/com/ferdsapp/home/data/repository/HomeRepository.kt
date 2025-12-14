package com.ferdsapp.home.data.repository

import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.source.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : IHomeRepository {
    override fun getNowMoviePlaying(): Flow<List<ResultNowPlayingResponses>> {
        TODO("Not yet implemented")
    }
}