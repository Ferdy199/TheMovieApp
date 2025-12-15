package com.ferdsapp.core.ui.helper

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.core.utils.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

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

    fun <T> Flow<ApiResponse<List<T>>>.asUiStateList(): Flow<UiState<List<T>>> =
        map { res ->
            when (res) {
                is ApiResponse.Loading -> UiState.Loading
                is ApiResponse.Success -> UiState.Success(res.data)
                is ApiResponse.Empty   -> UiState.Success(emptyList())
                is ApiResponse.Error   -> UiState.Error(res.errorMessage)
            }
        }.onStart { emit(UiState.Loading) }

    fun <T> Flow<ApiResponse<T>>.asUiState(): Flow<UiState<T>> =
        map { res ->
            when (res) {
                is ApiResponse.Loading -> UiState.Loading
                is ApiResponse.Success -> UiState.Success(res.data)
                is ApiResponse.Empty   -> UiState.Error(res.toString())
                is ApiResponse.Error   -> UiState.Error(res.errorMessage)
            }
        }.onStart { emit(UiState.Loading) }
}