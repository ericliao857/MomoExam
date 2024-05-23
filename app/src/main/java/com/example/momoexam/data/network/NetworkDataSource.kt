package com.example.momoexam.data.network

import com.example.momoexam.vo.ApiBean
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    suspend fun loadAreaIntroduction(): Flow<ApiBean<AreaIntroduction>>

    suspend fun loadAnimalInfo(): Flow<ApiBean<AnimalInfo>>
}