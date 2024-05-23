package com.example.momoexam.vo


import com.google.gson.annotations.SerializedName

/**
 * API 的外層架構
 */
data class ApiBean<T>(
    @SerializedName("result")
    val resultBean: ResultBean<T>
)