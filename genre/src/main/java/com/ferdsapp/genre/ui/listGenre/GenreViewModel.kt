package com.ferdsapp.genre.ui.listGenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiStateList
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.genre.data.model.GenresMovie
import com.ferdsapp.genre.data.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repository: IMovieRepository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<GenresMovie>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<GenresMovie>>>
        get() = _uiState

    fun getMovieListGenre() {
        viewModelScope.launch {
            repository.getMovieListGenre().asUiStateList()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { listGenre ->
                    _uiState.value = listGenre
                }
        }
    }
}