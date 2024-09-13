package com.app.vgtask.ui.models

enum class TripStyle(val displayName: String) {
    SOLO("Solo"),
    COUPLE("Couple"),
    FAMILY("Family"),
    GROUP("Group"),
    ;

    companion object{
        fun getStyle(string: String): TripStyle? {
            return entries.find { it.displayName == string }
        }
    }
}