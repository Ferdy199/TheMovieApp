package com.ferdsapp.detail.data.model.movie_genre

import com.google.gson.annotations.SerializedName


data class GenresMovie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String? = ""
)