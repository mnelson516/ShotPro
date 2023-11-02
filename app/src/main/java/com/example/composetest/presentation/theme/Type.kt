package com.example.composetest.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composetest.R


val OpenSans = FontFamily(
    Font(R.font.opensans_light, FontWeight.Light),
    Font(R.font.opensans_medium, FontWeight.Medium),
    Font(R.font.opensans_regular, FontWeight.Normal),
    Font(R.font.opensans_semibold, FontWeight.SemiBold),
    Font(R.font.opensans_bold, FontWeight.W700)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.White
    ),
    h2 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = Color.White
    ),
    h3 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        color = Color.White
    ),
    h4 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = Color.White
    ),
    h5 = TextStyle(
        fontFamily = OpenSans,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = Color.White
    ),

)