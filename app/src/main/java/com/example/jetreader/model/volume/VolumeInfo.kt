package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class VolumeInfo(
    val authors: List<String>? = null,
    @get:PropertyName("average_rating")
    @set:PropertyName("average_rating")
    var averageRating: Int? = 3,

    val categories: List<String>? = null,
    val description: String? = "",
    @get:PropertyName("image_links")
    @set:PropertyName("image_links")
    var imageLinks: ImageLinks? = null,

    @get:PropertyName("page_count")
    @set:PropertyName("page_count")
    var pageCount: Int? = null,

    @get:PropertyName("preview_link")
    @set:PropertyName("preview_link")
    var previewLink: String? = null,

    @get:PropertyName("print_type")
    @set:PropertyName("print_type")
    var printType: String? = "",

    @get:PropertyName("published_date")
    @set:PropertyName("published_date")
    var publishedDate: String? = "",

    val publisher: String? = "",
    @get:PropertyName("ratings_count")
    @set:PropertyName("ratings_count")
    var ratingsCount: Double? = 4.2,
    val subtitle: String? = "",
    val title: String? = ""
)