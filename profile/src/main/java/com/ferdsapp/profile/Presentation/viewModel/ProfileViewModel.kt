package com.ferdsapp.profile.Presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferdsapp.core.ui.state.UiState
import com.ferdsapp.profile.model.data.ProfileDataEntities
import com.ferdsapp.profile.model.repository.IProfileRepositories
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(val repositories: IProfileRepositories): ViewModel() {
    private val _uiState : MutableStateFlow<UiState<ProfileDataEntities>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ProfileDataEntities>>
        get() = _uiState

    fun getProfileData(){
        viewModelScope.launch {
            repositories.getProfileData()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}