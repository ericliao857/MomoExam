package com.example.momoexam.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momoexam.data.OpenDataRepository
import com.example.momoexam.utils.WhileUiSubscribed
import com.example.momoexam.vo.introduction.AreaIntroduction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<AreaIntroduction> = emptyList(),
    val userMessage: String? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: OpenDataRepository
) : ViewModel() {

    val uiState = repository.getAreaIntroduction().map {
        HomeUiState(
            isLoading = false,
            items = it
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = HomeUiState(isLoading = true)
    )
}