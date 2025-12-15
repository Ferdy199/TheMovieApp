package com.ferdsapp.genre.data.model

import com.google.gson.annotations.SerializedName

data class ResultMovieGenre(
    @SerializedName("id")
    val id: Int,

    @SerializedName("backdrop_path")
    val backdrop_path: String?,

    @SerializedName("original_title")
    val original_title: String?
)