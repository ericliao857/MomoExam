package com.example.momoexam.vo


import com.google.gson.annotations.SerializedName

/**
 * API 回傳資料架構
 */
data class ResultBean<T>(
    @SerializedName("count")
    val count: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("sort")
    val sort: String
)