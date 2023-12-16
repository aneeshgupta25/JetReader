package com.example.jetreader.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


object AppConstants {
    val DarkYellow = Color(0xfffca628)
    val LightYellow = Color(0xfffef4e6)
    val MediumGray = Color(0xff35383f)
    val DarkGray = Color(0xff181a20)
    const val BASE_URL = "https://www.googleapis.com/books/v1/"

    @Composable
    fun getScreenHeightInDp(): Dp {
        return LocalConfiguration.current.screenHeightDp.dp
    }

    @Composable
    fun getScreenWidthInDp(): Dp {
        return LocalConfiguration.current.screenWidthDp.dp
    }
}