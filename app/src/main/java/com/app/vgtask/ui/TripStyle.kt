package com.app.vgtask.ui

enum class TripStyle(val string: String) {
    SOLO("Solo"),
    COUPLE("Couple"),
    FAMILY("Family"),
    GROUP("Group"),
    ;

    companion object{
        fun getStyle(string: String): TripStyle? {
            return entries.find { it.string == string }
        }
    }
}