package com.ferdsapp.home.data.repository

import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {
    fun getNowMoviePlaying() : Flow<List<ResultNowPlayingResponses>>
}