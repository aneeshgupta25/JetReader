package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class ImageLinks(
    @get:PropertyName("small_thumbnail")
    @set:PropertyName("small_thumbnail")
    var smallThumbnail: String? = null,
    val thumbnail: String? = null
)