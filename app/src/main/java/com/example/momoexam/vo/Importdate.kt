package com.example.momoexam.vo


import com.google.gson.annotations.SerializedName

/**
 * 時間時區資料 (兩個API都是同個資料架構)
 */
data class Importdate(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
)