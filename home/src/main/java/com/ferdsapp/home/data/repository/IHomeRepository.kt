package com.ferdsapp.home.data.repository

import androidx.paging.PagingData
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    suspend fun getNowMoviePlaying() : Flow<PagingData<ResultNowPlayingResponses>>
}