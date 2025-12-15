package com.ferdsapp.genre.data.model

import com.google.gson.annotations.SerializedName


data class MovieGenreResponse(
    @SerializedName("genres")
    val genres: List<GenresMovie>
)