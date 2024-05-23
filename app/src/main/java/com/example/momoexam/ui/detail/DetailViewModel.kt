package com.example.momoexam.ui.detail

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.momoexam.R
import com.example.momoexam.data.OpenDataRepository
import com.example.momoexam.utils.Result
import com.example.momoexam.utils.WhileUiSubscribed
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class DetailUiState(
    val isLoading: Boolean = false,
    val area: AreaIntroduction? = null,
    val animals: List<AnimalInfo> = emptyList()
)

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: OpenDataRepository
) : ViewModel() {
    private val _savedArea: Flow<AreaIntroduction?> =
        savedStateHandle.getStateFlow(ARG_AREA, null)
    private val _filteredAnimal = combine(
        repository.getAnimalInfo(), _savedArea
    ) { animals, area ->
        filterAnimal(animals, area)
    }

    val uiState: StateFlow<DetailUiState> = combine(
        _savedArea, _filteredAnimal
    ) { area, animal ->
        DetailUiState(
            isLoading = false,
            area = area,
            animals = animal
        )
    }.stateIn(
        scope = viewModelScope,
        started = WhileUiSubscribed,
        initialValue = DetailUiState(isLoading = true)
    )

    private fun filterAnimal(animals: List<AnimalInfo>, area: AreaIntroduction?): List<AnimalInfo> {
        return animals.filter { it.aLocation == area?.eName }
    }

}