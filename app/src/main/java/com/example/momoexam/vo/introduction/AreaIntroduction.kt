package com.example.momoexam.vo.introduction


import android.os.Parcelable
import com.example.momoexam.vo.ImportDate
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * 館區介紹
 */
@Parcelize
data class AreaIntroduction(
    @SerializedName("e_category")
    val eCategory: String,
    @SerializedName("e_geo")
    val eGeo: String,
    @SerializedName("e_info")
    val eInfo: String,
    @SerializedName("e_memo")
    val eMemo: String? = null,
    @SerializedName("e_name")
    val eName: String,
    @SerializedName("e_no")
    val eNo: String,
    @SerializedName("e_pic_url")
    var ePicUrl: String,
    @SerializedName("e_url")
    val eUrl: String,
    @SerializedName("_id")
    val id: Int,
    @SerializedName("_importdate")
    val importDate: ImportDate
): Parcelable