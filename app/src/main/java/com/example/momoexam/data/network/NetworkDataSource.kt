package com.example.momoexam.data.network

import com.example.momoexam.vo.ApiBean
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.flow.Flow

interface NetworkDataSource {

    fun loadAreaIntroduction(): Flow<ApiBean<AreaIntroduction>>

    fun loadAnimalInfo(): Flow<ApiBean<AnimalInfo>>
}