package com.app.vgtask

import android.icu.util.Calendar
import java.text.SimpleDateFormat

fun generateCalendarItems(startAt: Long? = null, numberOfMonthsIntoTheFuture: Int = 10): List<CalendarItemsMonthGroup>{
    val monthCalendar =
        startAt?.let { Calendar.getInstance().apply { timeInMillis = startAt } } ?:
        Calendar.getInstance()
    var month = 1
    val monthGroups = mutableListOf<CalendarItemsMonthGroup>()

    while (month <= numberOfMonthsIntoTheFuture){
        addItemsForMonth(monthCalendar, monthGroups)
        monthCalendar.roll(Calendar.MONTH, 1)
        monthCalendar.synchronizeYearField()
        month++
    }

    return monthGroups
}

private fun addItemsForMonth(
    thisMonthCalendar: Calendar,
    monthGroups: MutableList<CalendarItemsMonthGroup>,
) {
    val items = mutableListOf<CalendarItem>()

    //add the day labels
    items.addAll(DayCalendarItem.getObjects())

    //set calendar to first day of the month
    thisMonthCalendar.set(Calendar.DAY_OF_MONTH, 1)

    var dayOfWeek = thisMonthCalendar.get(Calendar.DAY_OF_WEEK)

    if (dayOfWeek != 2) {
        addExtrasFromPreviousMonth(thisMonthCalendar, dayOfWeek, items)
    }

    val lastDateOfCurrentMonth = thisMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    for (date in 1..lastDateOfCurrentMonth) {
        items.add(CalendarItem(date.toString(), date, true, getDateValues(thisMonthCalendar, date)))
        if (date != lastDateOfCurrentMonth) {
            if (dayOfWeek == 7) dayOfWeek = 1 else dayOfWeek++
        }
    }

    if (dayOfWeek != 1) {
        addExtrasFromFollowingMonth(dayOfWeek, items)
    }

    val monthNumber = thisMonthCalendar.get(Calendar.MONTH)
    val year = thisMonthCalendar.get(Calendar.YEAR)
    val monthName = getMonthFromMonthNumber(monthNumber)
    val monthGroup = CalendarItemsMonthGroup("$monthName $year", items)
    monthGroups.add(monthGroup)
}

private fun addExtrasFromFollowingMonth(
    dayOfWeek: Int,
    items: MutableList<CalendarItem>
) {
    val nextMonthOverFlow = 8 - dayOfWeek
    for (date in 1..nextMonthOverFlow) {
        items.add(CalendarItem(date.toString(), date))
    }
}

private fun addExtrasFromPreviousMonth(
    calendar: Calendar,
    dayOfWeek: Int,
    items: MutableList<CalendarItem>
) {
    val prevMonthOverflow =  if(dayOfWeek == 1) 6 else dayOfWeek - 2
    val prevMonthCalendar = Calendar.getInstance().apply {
        timeInMillis = calendar.timeInMillis
        roll(Calendar.MONTH, -1)
        synchronizeYearField()
    }
    val maxDayInPrevMonth = prevMonthCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfPrevMonthOverFlow = maxDayInPrevMonth - prevMonthOverflow + 1
    for (day in firstDayOfPrevMonthOverFlow..maxDayInPrevMonth) {
        items.add(CalendarItem(day.toString(), day))
    }
}

private fun Calendar.synchronizeYearField() {
    if (get(Calendar.MONTH) == 0) {
        val year = get(Calendar.YEAR)
        set(Calendar.YEAR, year + 1)
    }
}

open class CalendarItem(
    val name: String,
    val value: Int? = null,
    val active: Boolean = false,
    private val dateValues: Triple<Int, Int, Int>? = null,
) {
    val clickable = value != null
    val formattedDate: String? get() {
        return dateValues?.let {
            val date = Calendar.getInstance().apply {
                set(Calendar.DAY_OF_MONTH, it.first)
                set(Calendar.MONTH, it.second)
                set(Calendar.YEAR, it.third)
            }.time
            SimpleDateFormat("EEE, MMM d").format(date)
        }
    }


}

sealed class DayCalendarItem(name: String): CalendarItem(name) {
    data object Monday: DayCalendarItem("Mo")
    data object Tuesday: DayCalendarItem("Tu")
    data object Wednesday: DayCalendarItem("We")
    data object Thursday: DayCalendarItem("Th")
    data object Friday: DayCalendarItem("Fr")
    data object Saturday: DayCalendarItem("Sa")
    data object Sunday: DayCalendarItem("Su")

    companion object {
        fun getObjects(): List<DayCalendarItem>{
            return listOf(Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday)
        }
    }
}

data class CalendarItemsMonthGroup(
    val month: String,
    val items: List<CalendarItem>
)

fun getMonthFromMonthNumber(monthNumber: Int): String{
    return when(monthNumber){
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> { throw Exception("invalid month number provided: $monthNumber")}
    }
}

fun getDateValues(calendar: Calendar, date: Int): Triple<Int, Int, Int>{
    return with(calendar){ Triple(date, get(Calendar.MONTH), get(Calendar.YEAR)) }
}

