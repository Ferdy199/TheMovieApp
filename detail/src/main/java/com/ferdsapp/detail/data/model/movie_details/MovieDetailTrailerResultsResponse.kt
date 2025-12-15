package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailTrailerResultsResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("key")
    val key: String,

    @SerializedName("type")
    val type: String?,

    @SerializedName("site")
    val site: String
)