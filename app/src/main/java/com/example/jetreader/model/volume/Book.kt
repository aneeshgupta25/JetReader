package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class Book(
    val etag: String? = "",
    val id: String? = "",
    @get:PropertyName("search_info")
    @set:PropertyName("search_info")
    var searchInfo: SearchInfo? = null,

    @get:PropertyName("volume_info")
    @set:PropertyName("volume_info")
    var volumeInfo: VolumeInfo? = null,

    @get:PropertyName("sale_info")
    @set:PropertyName("sale_info")
    var saleInfo: SaleInfo? = null
)