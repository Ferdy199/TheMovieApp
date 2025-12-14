package com.ferdsapp.detail.data.model.movie_genre

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(
    @SerializedName("genres")
    val genres: List<GenresMovie>
)