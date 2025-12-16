package com.ferdsapp.genre.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class ListMovieGenreResponse(
    @SerializedName("page")
    val page: Int,

    @SerializedName("results")
    val results: List<ResultMovieGenre>?,

    @SerializedName("total_pages")
    val total_pages: Int,
)