package com.example.momoexam.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateUtils {

    const val PATTERN_yyyy_MM_dd_SLASH = "yyyy/MM/dd"     // 2022/12/23
    const val PATTERN_yyyy_MM_dd_T_HH_mm_ss_SSSSSS_DASH =
        "yyyy-MM-dd HH:mm:ss.SSSSSS"     // 2022-12-23 14:05:14.684926

    /**
     * 格式化日期 (API 26以下)
     * - 有資料是空的返回原本日期，出現錯誤也是返回原本日期字串
     * @param dateStr (日期字串)
     * @param oldPattern (原本日期格式)
     * @param newPattern (要轉換日期格式)
     */
    @JvmStatic
    fun formatData(
        dateStr: String,
        oldPattern: String,
        newPattern: String,
        timeZone: String = "UTC"
    ): String {
        if (dateStr.isEmpty() || oldPattern.isEmpty() || newPattern.isEmpty()) {
            return dateStr
        }

        return try {
            val tz = TimeZone.getTimeZone(timeZone)
            val oldFormat = SimpleDateFormat(
                oldPattern,
                Locale.getDefault()
            ).apply { this.timeZone = tz }
            val date = oldFormat.parse(dateStr) ?: return dateStr
            val newFormat = SimpleDateFormat(
                newPattern,
                Locale.getDefault()
            ).apply { this.timeZone = tz }
            newFormat.format(date)
        } catch (e: Exception) {
            dateStr
        }
    }
}