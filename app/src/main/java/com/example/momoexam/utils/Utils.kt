package com.example.momoexam.utils

object Utils {

    /**
     * 合併字串 (如果append字串為空就直接回原本字串)
     */
    fun mergeStringsWithSeparator(
        baseString: String,
        appendString: String,
        separator: String = ","
    ): String {
        return if (appendString.isNotEmpty()) {
            "$baseString$separator$appendString"
        } else {
            baseString
        }
    }
}