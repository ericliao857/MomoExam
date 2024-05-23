package com.example.momoexam.vo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * 時間時區資料 (兩個API都是同個資料架構)
 */
@Parcelize
data class ImportDate(
    @SerializedName("date")
    val date: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("timezone_type")
    val timezoneType: Int
): Parcelable