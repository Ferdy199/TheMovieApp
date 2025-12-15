package com.ferdsapp.movieapp.presentation.navigation

sealed class Screen(val route: String){
    data object Home : Screen("home")
    data object Detail: Screen("detail")
    data object Genre: Screen("genre")
}