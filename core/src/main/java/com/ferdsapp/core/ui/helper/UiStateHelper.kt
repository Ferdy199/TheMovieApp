package com.ferdsapp.core.ui.helper

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ferdsapp.core.ui.state.UiState

object UiStateHelper {
    fun <T : Any> LazyPagingItems<T>.asUiState(): UiState<Unit> {
        return when (val refresh = loadState.refresh) {
            is LoadState.Loading -> UiState.Loading
            is LoadState.Error -> UiState.Error(refresh.error.message ?: "Error")
            else -> {
                if (itemCount == 0) UiState.Empty
                else UiState.Success(Unit)
            }
        }
    }
}