package com.app.vgtask.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.vgtask.R

val black = FontFamily(Font(R.font.satoshi_black))
val bold = FontFamily(Font(R.font.satoshi_bold))
val light = FontFamily(Font(R.font.satoshi_light))
val medium = FontFamily(Font(R.font.satoshi_medium))
val regular = FontFamily(Font(R.font.satoshi_regular))

// Set of Material typography styles to start with
val Typography = Typography(

    headlineLarge = TextStyle(
        fontFamily = bold,
        fontSize = 18.sp
    ),

    headlineMedium = TextStyle(
        fontFamily = black,
        fontSize = 16.sp
    ),

    headlineSmall = TextStyle(
        fontFamily = bold,
        fontSize = 16.sp
    ),

    bodyLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = black,
    ),

    labelLarge = TextStyle(
        fontFamily = medium,
        fontSize = 16.sp
    ),

    labelMedium = TextStyle(
        fontFamily = medium,
        fontSize = 12.sp
    )
)