package com.mobile.goclean.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mobile.goclean.R

val productsans =
    FontFamily(
        Font(R.font.productsans, FontWeight.Normal),
    )

val ooredo =
    FontFamily(
        Font(R.font.ooredo, FontWeight.Normal),
    )

val Typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = productsans,
            ),
        titleLarge =
            TextStyle(
                fontFamily = ooredo,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp,
            ),
    )
