package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class SearchInfo(
    @get:PropertyName("text_snippet")
    val textSnippet: String? = null
)