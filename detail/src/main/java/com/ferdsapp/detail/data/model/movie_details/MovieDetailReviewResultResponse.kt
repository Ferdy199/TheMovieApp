package com.ferdsapp.detail.data.model.movie_details

import com.google.gson.annotations.SerializedName

data class MovieDetailReviewResultResponse(
    @SerializedName("author")
    val author: String?,

    @SerializedName("author_details")
    val author_details : MovieDetailReviewAuthorDetails,

    @SerializedName("total_pages")
    val total_pages: Int?
)