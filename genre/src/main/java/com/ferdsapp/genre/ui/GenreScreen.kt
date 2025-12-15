package com.ferdsapp.genre.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.genre.component.ListGenreItem
import com.ferdsapp.genre.data.model.GenresMovie

@Composable
fun GenreScreen(
    viewModel: GenreViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Empty -> EmptyDialog()
            is UiState.Error -> EmptyDialog()
            is UiState.Loading -> {
                viewModel.getMovieListGenre()
            }
            is UiState.Success -> {
                val data = uiState.data
                GenreScreenContent(
                    data
                )
            }
        }
    }
}

@Composable
fun GenreScreenContent(
    listGenre: List<GenresMovie>,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Movie Now Playing",
        color = Color.Black,
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(Modifier.height(16.dp))
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(listGenre) { genreList ->
            ListGenreItem(
                genre = genreList.name ?: "-"
            )
        }
    }
}