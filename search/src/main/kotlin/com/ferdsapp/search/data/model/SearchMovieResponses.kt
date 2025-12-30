package com.ferdsapp.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResponses(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<SearchMovieResult>? = listOf(),

    @SerializedName("total_pages")
    val total_pages: Int
)