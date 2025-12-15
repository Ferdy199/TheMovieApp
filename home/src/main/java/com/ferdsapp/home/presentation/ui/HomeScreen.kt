package com.ferdsapp.home.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.core.ui.theme.MovieAppTheme
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.presentation.component.MovieListItem

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val state = homeViewModel.nowPlayingMovie.collectAsLazyPagingItems()
    when(state.asUiState()){
        is UiState.Empty -> {
            EmptyDialog()
        }
        is UiState.Error -> {
            EmptyDialog()
        }
        is UiState.Loading -> {
            LoadingDialog()
        }
        is UiState.Success -> {
            val nowPlayingData = state
            HomeScreenContent(nowPlayingData = nowPlayingData)
        }
    }
}

@Composable
fun HomeScreenContent(
    nowPlayingData: LazyPagingItems<ResultNowPlayingResponses>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Movie Now Playing",
            color = Color.Black,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(nowPlayingData.itemCount, key = {index -> nowPlayingData[index]?.id ?: index }){ movieResponses ->
                val movieData = nowPlayingData[movieResponses] ?: return@items
                MovieListItem(
                    backdrop_path = movieData.backdrop_path,
                    title = movieData.title
                )
            }
        }
    }
}