package com.ferdsapp.search.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.ferdsapp.core.ui.theme.MovieAppTheme

@Composable
fun SearchMovieScreen(modifier: Modifier = Modifier) {
    SearchMovieScreenContent()
}

@Composable
fun SearchMovieScreenContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "Search Screen",
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun SearchMoviePreview() {
    MovieAppTheme {
        SearchMovieScreenContent()
    }
}