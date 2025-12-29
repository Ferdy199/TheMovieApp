package com.ferdsapp.search.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ferdsapp.core.ui.theme.MovieAppTheme
import com.ferdsapp.search.presentation.component.MovieSearchBar

@Composable
fun SearchMovieScreen(modifier: Modifier = Modifier) {
    SearchMovieScreenContent()
}

@Composable
fun SearchMovieScreenContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            modifier = Modifier
        ) {
            MovieSearchBar(
                query = "",
                onQueryChange = {}
            )
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}


@Preview(showBackground = true)
@Composable
private fun SearchMoviePreview() {
    MovieAppTheme {
        SearchMovieScreenContent()
    }
}