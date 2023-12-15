package com.example.jetreader.model

import com.example.jetreader.model.volume.Book
import com.google.firebase.Timestamp
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MBook(
    @Exclude var id: String? = null,

    @get:PropertyName("user_id")
    @set:PropertyName("user_id")
    var userId: String? = null,

    var book: Book? = null
)
