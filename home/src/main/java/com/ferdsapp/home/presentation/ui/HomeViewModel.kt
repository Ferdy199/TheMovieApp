package com.ferdsapp.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ferdsapp.home.data.model.now_playing.ResultNowPlayingResponses
import com.ferdsapp.home.data.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IHomeRepository): ViewModel() {
    private val _uiState: MutableStateFlow<PagingData<ResultNowPlayingResponses>> =
        MutableStateFlow(PagingData.empty())

    val uiState: StateFlow<PagingData<ResultNowPlayingResponses>>
        get() = _uiState

    fun getNowPlayingResponse() = viewModelScope.launch {
        repository.getNowMoviePlaying()
            .cachedIn(viewModelScope)
            .collect {
                _uiState.value = it
            }
    }

}