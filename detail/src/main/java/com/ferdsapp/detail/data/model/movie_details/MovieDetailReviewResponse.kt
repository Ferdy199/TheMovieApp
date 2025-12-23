package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailReviewResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDetailReviewResultResponse>?,

    @SerializedName("total_pages")
    val total_pages: Int?
)