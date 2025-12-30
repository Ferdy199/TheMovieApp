package com.ferdsapp.movieapp.presentation.navigation

sealed class Screen(val route: String){
    data object Home : Screen("home")
    data object Detail: Screen("detail/{movieId}"){
        fun createRoute(
            movieId: Int
        ) = "detail/$movieId"
    }
    data object Genre: Screen("genre")

    data object Search: Screen("search")

    data object Profile: Screen("profile")
    data object ListMovieGenre: Screen("genre/{with_genres}/{genres_name}"){
        fun createRoute(
            with_genres: String,
            genres_name: String
        ) = "genre/$with_genres/$genres_name"
    }
}