package com.example.momoexam.ui.animal

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momoexam.utils.WhileUiSubscribed
import com.example.momoexam.vo.animal.AnimalInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class AnimalDetailUiState(
    val isLoading: Boolean = false,
    val animal: AnimalInfo? = null
)

@HiltViewModel
class AnimalDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val uiState: StateFlow<AnimalDetailUiState> =
        savedStateHandle.getStateFlow<AnimalInfo?>(ARG_ANIMAL, null)
            .map {
                AnimalDetailUiState(
                    isLoading = false,
                    animal = it
                )
            }.stateIn(
                scope = viewModelScope,
                started = WhileUiSubscribed,
                initialValue = AnimalDetailUiState(isLoading = true)
            )
}