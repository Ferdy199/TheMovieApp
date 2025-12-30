package com.ferdsapp.movieapp.presentation.ui.app

import androidx.compose.ui.graphics.vector.ImageVector
import com.ferdsapp.movieapp.presentation.navigation.Screen

data class MenuItem(
    val title: String,
    val icon: ImageVector,
    val screen: Screen
)