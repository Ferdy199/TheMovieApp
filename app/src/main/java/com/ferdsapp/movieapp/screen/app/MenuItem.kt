package com.ferdsapp.movieapp.screen.app

import androidx.compose.ui.graphics.vector.ImageVector
import com.ferdsapp.movieapp.navigation.Screen

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)