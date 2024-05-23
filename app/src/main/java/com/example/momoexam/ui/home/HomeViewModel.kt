package com.example.momoexam.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momoexam.R
import com.example.momoexam.data.OpenDataRepository
import com.example.momoexam.utils.Result
import com.example.momoexam.utils.WhileUiSubscribed
import com.example.momoexam.vo.introduction.AreaIntroduction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class HomeUiState(
    val isLoading: Boolean = false,
    val items: List<AreaIntroduction> = emptyList(),
    val message: Int? = null
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: OpenDataRepository
) : ViewModel() {
    private val _getAreaIntroduction = repository.getAreaIntroduction()
        .map { handleAreaIntroduction(it) }
        .catch { emit(Result.Error(R.string.load_area_error)) }
    val uiState: StateFlow<HomeUiState> = _getAreaIntroduction.map {
        when(it) {
            Result.Loading -> {
                HomeUiState(
                    isLoading = true
                )
            }

            is Result.Error -> {
                HomeUiState(
                    isLoading = false,
                    message = it.errorMessage
                )
            }
            is Result.Success -> {
                HomeUiState(
                    isLoading = false,
                    items = it.data
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = HomeUiState(isLoading = true)
    )

    private fun handleAreaIntroduction(areaIntroductions: List<AreaIntroduction>): Result<List<AreaIntroduction>> {
        if (areaIntroductions.isEmpty()) {
            return Result.Error(R.string.not_find_area)
        }
        return Result.Success(areaIntroductions)
    }
}