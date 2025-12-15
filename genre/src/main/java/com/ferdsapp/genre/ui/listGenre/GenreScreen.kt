package com.ferdsapp.genre.ui.listGenre

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.genre.component.ListGenreItem
import com.ferdsapp.genre.data.model.GenresMovie

@Composable
fun GenreScreen(
    viewModel: GenreViewModel = hiltViewModel(),
    navigateToListMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Empty -> EmptyDialog()
            is UiState.Error -> {
                Log.d("Genre", "GenreScreen: ${uiState.errorMessage}")
                EmptyDialog()
            }
            is UiState.Loading -> {
                viewModel.getMovieListGenre()
            }
            is UiState.Success -> {
                val data = uiState.data
                GenreScreenContent(
                    data,
                    navigateToListMovie
                )
            }
        }
    }
}

@Composable
fun GenreScreenContent(
    listGenre: List<GenresMovie>,
    navigateToListMovie: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(listGenre) { genreList ->
            ListGenreItem(
                genre = genreList.name ?: "-",
                modifier = Modifier.clickable{
                    navigateToListMovie(genreList.id.toString())
                }
            )
        }
    }
}