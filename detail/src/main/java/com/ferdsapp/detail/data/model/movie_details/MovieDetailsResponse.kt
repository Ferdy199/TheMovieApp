package com.ferdsapp.detail.data.model.movie_details

import com.ferdsapp.detail.data.model.movie_genre.GenresMovie
import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("backdrop_path")
    val backdrop_path: String?,

    @SerializedName("genres")
    val genres: List<GenresMovie>?,

    @SerializedName("original_title")
    val original_title: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val vote_average: Float?,

    val movieTrailer: MovieDetailTrailersResponse?,

    val movieReview : MovieDetailReviewResponse?
)