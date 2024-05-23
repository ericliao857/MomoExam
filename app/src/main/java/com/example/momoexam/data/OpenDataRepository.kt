package com.example.momoexam.data

import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.flow.Flow

interface OpenDataRepository {
    fun getAreaIntroduction(): Flow<List<AreaIntroduction>>

    fun getAnimalInfo(): Flow<List<AnimalInfo>>
}