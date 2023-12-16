package com.example.jetreader.model.pager

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

data class MyPager(
    val img: Int,
    val description: String,
    val title: AnnotatedString
)
