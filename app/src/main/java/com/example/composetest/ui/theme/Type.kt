package com.example.composetest.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetest.R


val gothicA1 = FontFamily(
    listOf(
        Font(R.font.gothic_semibold, FontWeight.SemiBold),
        Font(R.font.gothic_black, FontWeight.Black),
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    h6 = TextStyle(
        fontFamily = gothicA1,
        fontWeight = FontWeight.SemiBold,
        fontSize = 12.sp,
        color = WhiteGray
    ),
    h5 = TextStyle(
        fontFamily = gothicA1,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        color = Color.White
    ),
    h4 = TextStyle(
        fontFamily = gothicA1,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        color = Color.White
    ),

)