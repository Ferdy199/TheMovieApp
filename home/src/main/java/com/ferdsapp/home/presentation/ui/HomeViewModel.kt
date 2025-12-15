package com.ferdsapp.home.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ferdsapp.home.data.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IHomeRepository): ViewModel() {
    val nowPlayingMovie =
        repository.getNowMoviePlaying()
            .cachedIn(viewModelScope)
}