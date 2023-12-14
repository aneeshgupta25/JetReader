package com.example.jetreader.model.volume

data class Volume(
    val items: List<Book>,
    val kind: String,
    val totalItems: Int
)