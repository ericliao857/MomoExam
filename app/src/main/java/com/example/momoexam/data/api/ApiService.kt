package com.example.momoexam.data.api

import com.example.momoexam.vo.ApiBean
import com.example.momoexam.vo.animal.AnimalInfo
import com.example.momoexam.vo.introduction.AreaIntroduction
import retrofit2.http.GET


interface ApiService {

    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    suspend fun getAreaIntroduction(): ApiBean<AreaIntroduction>

    @GET("a3e2b221-75e0-45c1-8f97-75acbd43d613?scope=resourceAquire")
    suspend fun getAnimalInfo(): ApiBean<AnimalInfo>

    companion object {
        const val DOMAIN = "https://data.taipei/api/v1/dataset/"

    }
}