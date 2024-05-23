package com.example.momoexam.data

import com.example.momoexam.data.network.NetworkDataSource
import com.example.momoexam.di.DefaultDispatcher
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class OpenDataRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
): OpenDataRepository {
    override suspend fun getAreaIntroduction(): Flow<List<AreaIntroduction>> {
        return networkDataSource.loadAreaIntroduction()
            .map { it.resultBean.results }
            .catch { it.printStackTrace() }
            .flowOn(dispatcher)
    }

    override suspend fun getAnimalInfo(): Flow<List<AnimalInfo>> {
        return networkDataSource.loadAnimalInfo()
            .map { it.resultBean.results }
            .catch { it.printStackTrace() }
            .flowOn(dispatcher)
    }
}