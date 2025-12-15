package com.ferdsapp.genre.ui.listMovieGenre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ferdsapp.genre.data.model.ResultMovieGenre
import com.ferdsapp.genre.data.repository.IMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListMovieGenreViewModel @Inject constructor(private val repository: IMovieRepository): ViewModel() {

    fun getListMovieGenre(with_genres: String): Flow<PagingData<ResultMovieGenre>> {
        val repo = repository.getListMovieGenre(with_genres)
            .cachedIn(viewModelScope)
        return repo
    }

}