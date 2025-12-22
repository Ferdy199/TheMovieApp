package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailReviewResultResponse(
    @SerializedName("author")
    val author: String?,

    @SerializedName("author_details")
    val author_details : MovieDetailReviewAuthorDetails,

    @SerializedName("content")
    val content: String?,

    @SerializedName("created_at")
    val created_at: String?,

    @SerializedName("id")
    val id: String,

    @SerializedName("updated_at")
    val updated_at: String?,

    @SerializedName("total_pages")
    val total_pages: Int?
)