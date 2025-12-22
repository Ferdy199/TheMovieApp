package com.ferdsapp.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ferdsapp.core.ui.helper.UiStateHelper.asUiState
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.core.utils.ApiResponse
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IHomeRepository): ViewModel() {
//    private val _uiState: MutableStateFlow<UiState<PagingData<ResultNowPlayingResponses>>> =
//        MutableStateFlow(UiState.Loading)
//
//    val uiState: StateFlow<UiState<PagingData<ResultNowPlayingResponses>>>
//        get() = _uiState
//
//    fun getNowPlayingResponse(){
//        viewModelScope.launch {
//            repository.getNowMoviePlaying().asUiState()
//                .catch {
//                    _uiState.value = UiState.Error(it.message.toString())
//                }
//                .collect { nowPlayingMovie ->
//                    _uiState.value = nowPlayingMovie
//                }
//        }
//    }

    val getNowPlayingResponse =
        repository.getNowMoviePlaying()
            .cachedIn(viewModelScope)
}