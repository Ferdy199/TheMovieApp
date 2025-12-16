package com.ferdsapp.home.presentation.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.presentation.component.MovieListItem

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = homeViewModel.nowPlayingMovie.collectAsLazyPagingItems()
    val uiState = state.asUiState()
    when(uiState){
        is UiState.Empty -> {
            EmptyDialog("Kosong aja")
        }
        is UiState.Error -> {
            Log.d("Error Home", "HomeScreen: ${uiState.errorMessage}")
            EmptyDialog("Error ${uiState.errorMessage}")
        }
        is UiState.Loading -> {
            LoadingDialog()
        }
        is UiState.Success -> {
            HomeScreenContent(nowPlayingData = state, navigateToDetail)
        }
    }
}

@Composable
fun HomeScreenContent(
    nowPlayingData: LazyPagingItems<ResultNowPlayingResponses>,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Movie Now Playing",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(nowPlayingData.itemCount, key = {index -> nowPlayingData[index]?.id ?: index }){ movieResponses ->
                val movieData = nowPlayingData[movieResponses] ?: return@items
                MovieListItem(
                    backdrop_path = movieData.backdrop_path,
                    title = movieData.title,
                    modifier = Modifier.clickable {
                        navigateToDetail(movieData.id)
                    }
                )
            }
        }
    }
}