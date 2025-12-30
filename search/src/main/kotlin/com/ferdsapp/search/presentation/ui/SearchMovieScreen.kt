package com.ferdsapp.search.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.ferdsapp.core.ui.component.EmptyDialog
import com.ferdsapp.core.ui.component.ErrorDialog
import com.ferdsapp.core.ui.component.LoadingDialog
import com.ferdsapp.core.ui.component.MovieListItem
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.search.presentation.component.MovieSearchBar

@Composable
fun SearchMovieScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchMovieScreenContent(viewModel, navigateToDetail)
}

@Composable
fun SearchMovieScreenContent(
    viewModel: SearchViewModel,
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val query by viewModel.query.collectAsState()
    val state = viewModel.searchState.collectAsLazyPagingItems()
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MovieSearchBar(
                query = query,
                onQueryChange = {
                    viewModel.onQueryChanged(it)
                },
                onSearch = {
                    viewModel.searchNow(it)
                },
                modifier.fillMaxWidth()
                    .heightIn(min = 48.dp)
                    .padding(horizontal = 8.dp, vertical = 0.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            when(state.asUiState()){
                is UiState.Empty -> EmptyDialog()
                is UiState.Error -> ErrorDialog()
                is UiState.Loading -> LoadingDialog()
                is UiState.Success -> {
                    LazyColumn {
                        items(
                            count = state.itemCount,
                            key = { index ->
                                val item = state[index]
                                "${item?.id ?: "null"}-$index"
                            }
                        ) { index ->
                            val movieData = state[index] ?: return@items
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
        }
    }
}