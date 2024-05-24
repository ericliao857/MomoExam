package com.example.momoexam.utils

import com.example.momoexam.data.OpenDataRepository
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import org.jetbrains.annotations.VisibleForTesting

class FakeOpenDataRepository : OpenDataRepository {
    private var shouldThrowError = false

    private val _savedAreaIntroductions = MutableStateFlow(emptyList<AreaIntroduction>())
    val savedAreaIntroductions: StateFlow<List<AreaIntroduction>> =
        _savedAreaIntroductions.asStateFlow()
    private val _savedAnimalInfos = MutableStateFlow(emptyList<AnimalInfo>())
    val savedAnimalInfos: StateFlow<List<AnimalInfo>> =
        _savedAnimalInfos.asStateFlow()

    fun setShouldThrowError(should: Boolean) {
        shouldThrowError = should
    }

    override fun getAreaIntroduction(): Flow<List<AreaIntroduction>> {
        return savedAreaIntroductions.map {
            if (shouldThrowError) {
                throw Exception("Test exception")
            } else {
                it.toList()
            }
        }
    }

    override fun getAnimalInfo(): Flow<List<AnimalInfo>> {
        return savedAnimalInfos.map {
            if (shouldThrowError) {
                throw Exception("Test exception")
            } else {
                it.toList()
            }
        }
    }

    @VisibleForTesting
    fun addAreaIntroductions(vararg newAreas: AreaIntroduction) {
        _savedAreaIntroductions.update { oldAreas ->
            buildList {
                addAll(oldAreas)
                addAll(newAreas)
            }
        }
    }

    @VisibleForTesting
    fun addAnimalInfos(vararg newAnimal: AnimalInfo) {
        _savedAnimalInfos.update { oldAnimals ->
            buildList {
                addAll(oldAnimals)
                addAll(newAnimal)
            }
        }
    }
}