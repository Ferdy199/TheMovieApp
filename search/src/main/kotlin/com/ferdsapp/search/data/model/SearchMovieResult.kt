package com.ferdsapp.search.data.model

import com.google.gson.annotations.SerializedName

data class SearchMovieResult(
    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdrop_path: String? = "",

    @SerializedName("id")
    val id: Int,

    @SerializedName("original_title")
    val original_title: String? = "",

    @SerializedName("title")
    val title: String? = ""
)