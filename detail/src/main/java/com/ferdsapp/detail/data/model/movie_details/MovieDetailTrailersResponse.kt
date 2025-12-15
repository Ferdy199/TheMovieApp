package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailTrailersResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("results")
    val results: List<MovieDetailTrailerResultsResponse>? = listOf()
)