package com.app.vgtask

import java.text.SimpleDateFormat
import java.util.Calendar

fun getFormattedDate(timestamp: Long, getOrdinalDate: Boolean = false): String{
    val calendar = Calendar.getInstance().apply { timeInMillis = timestamp }

    if (!getOrdinalDate){
        val format = SimpleDateFormat("dd MMM yyyy")
        return format.format(calendar.time)
    }

    val date = calendar.get(Calendar.DATE)
    val ordinalDate = addSuffixToDate(date)
    val format = SimpleDateFormat("MMM yyyy")
    val monthAndYear = format.format(calendar.time)

    return "$ordinalDate $monthAndYear"
}

private fun addSuffixToDate(date: Int): String {
    return when (date) {
        in intArrayOf(1, 21, 31) -> "${date}st"
        in intArrayOf(2, 22) -> "${date}nd"
        in intArrayOf(3, 23) -> "${date}rd"
        else -> {"${date}th"}
    }
}