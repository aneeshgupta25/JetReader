package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class Volume(
    val items: List<Book>,
    val kind: String,
    @get:PropertyName("total_items")
    @set:PropertyName("total_items")
    var totalItems: Int
)