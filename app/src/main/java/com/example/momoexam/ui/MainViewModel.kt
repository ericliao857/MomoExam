package com.example.momoexam.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MainUiState(
    val toolbarTitle: String = ""
)
@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {
    private val _uiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState())
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    /**
     * 設定toolbar title
     */
    fun setToolbarTitle(title: String) {
        _uiState.update {
            it.copy(toolbarTitle = title)
        }
    }
}