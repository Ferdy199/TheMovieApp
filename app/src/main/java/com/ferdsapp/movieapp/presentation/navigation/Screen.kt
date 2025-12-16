package com.ferdsapp.movieapp.presentation.navigation

sealed class Screen(val route: String){
    data object Home : Screen("home")
    data object Detail: Screen("detail/{movieId}"){
        fun createRoute(
            movieId: Int
        ) = "detail/$movieId"
    }
    data object Genre: Screen("genre")
    data object ListMovieGenre: Screen("genre/{with_genres}"){
        fun createRoute(
            with_genres: String
        ) = "genre/$with_genres"
    }
}