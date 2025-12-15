package com.ferdsapp.movieapp.presentation.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ferdsapp.core.ui.theme.MovieAppTheme
import com.ferdsapp.movieapp.presentation.screen.app.MovieApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                MovieApp()
            }
        }
    }
}