package com.ferdsapp.detail.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiStateList
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.detail.data.model.movie_details.MovieDetailsResponse
import com.ferdsapp.detail.data.repository.IDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: IDetailRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<MovieDetailsResponse>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<MovieDetailsResponse>>
        get() = _uiState

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getDetailRepository(movieId).asUiState()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { detailMovie ->
                    _uiState.value = detailMovie
                }
        }
    }
}