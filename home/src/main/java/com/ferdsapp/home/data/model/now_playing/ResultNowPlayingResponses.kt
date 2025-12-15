package com.ferdsapp.home.data.model.now_playing

import com.google.gson.annotations.SerializedName

data class ResultNowPlayingResponses(
    @SerializedName("id")
    var id: Int,

    @SerializedName("backdrop_path")
    var backdrop_path: String? = "",

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("vote_average")
    var vote_average: Float? = 0F
)