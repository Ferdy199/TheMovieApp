package com.ferdsapp.home.data.model.now_playing

import com.google.gson.annotations.SerializedName

data class NowPlayingMovieResponses(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<ResultNowPlayingResponses>,

    @SerializedName("total_pages")
    val total_pages: Int
)