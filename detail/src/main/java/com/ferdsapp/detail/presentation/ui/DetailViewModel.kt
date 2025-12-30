package com.ferdsapp.detail.presentation.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.detail.data.model.movie_details.MovieDetailReviewResultResponse
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

    private val _uiMovieReviewState: MutableStateFlow<PagingData<MovieDetailReviewResultResponse>> =
        MutableStateFlow(PagingData.empty())
    val uiMovieReviewState: StateFlow<PagingData<MovieDetailReviewResultResponse>>
        get() = _uiMovieReviewState

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

    fun getMovieReviewResponse(movieId: Int){
        viewModelScope.launch {
            repository.getMovieReviewResponse(movieId)
                .cachedIn(viewModelScope)
                .catch {
                    Log.d("Detail Error", "${it.message.toString()} ${it.stackTrace}")
                }
                .collect {
                    _uiMovieReviewState.value = it
                }
        }
    }
}