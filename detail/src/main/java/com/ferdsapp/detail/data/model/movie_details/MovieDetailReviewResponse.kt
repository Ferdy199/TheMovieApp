package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailReviewResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<MovieDetailReviewResultResponse>?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("created_at")
    val created_at: String?,

    @SerializedName("updated_at")
    val updated_at: String?,

    @SerializedName("totalPage")
    val totalPage: Int?
)