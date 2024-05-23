package com.example.momoexam.data

import com.example.momoexam.data.network.NetworkDataSource
import com.example.momoexam.di.DefaultDispatcher
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class OpenDataRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) : OpenDataRepository {
    override fun getAreaIntroduction(): Flow<List<AreaIntroduction>> {
        return networkDataSource.loadAreaIntroduction()
            .map {
                // e_pic_url 的連結Http的掛了，在這裡全部替換成Https
                it.resultBean.results
                    .map { introduction ->
                        introduction.apply {
                            ePicUrl =
                                ePicUrl.replace("http://", "https://")
                        }
                    }
            }
            .flowOn(dispatcher)
    }

    override fun getAnimalInfo(): Flow<List<AnimalInfo>> {
        return networkDataSource.loadAnimalInfo()
            .map {
                // 照片連結Http的掛了，在這裡全部替換成Https
                it.resultBean.results
                    .map { animal ->
                        animal.apply {
                            aPic01Url = aPic01Url.replace("http://", "https://")
                        }
                    }
            }
            .flowOn(dispatcher)
    }
}