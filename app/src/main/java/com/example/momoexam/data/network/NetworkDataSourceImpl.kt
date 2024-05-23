package com.example.momoexam.data.network

import com.example.momoexam.data.api.ApiService
import com.example.momoexam.di.IoDispatcher
import com.example.momoexam.vo.ApiBean
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val apiService: ApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): NetworkDataSource {
    override fun loadAreaIntroduction(): Flow<ApiBean<AreaIntroduction>> = flow {
        emit(apiService.getAreaIntroduction())
    }.flowOn(ioDispatcher)


    override fun loadAnimalInfo(): Flow<ApiBean<AnimalInfo>> = flow {
        emit(apiService.getAnimalInfo())
    }.flowOn(ioDispatcher)

}