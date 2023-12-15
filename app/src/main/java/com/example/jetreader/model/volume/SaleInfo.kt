package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class SaleInfo(
    @get:PropertyName("buy_link")
    @set:PropertyName("buy_link")
    var buyLink: String? = "",
    val country: String? = "",
    @get:PropertyName("is_ebook")
    @set:PropertyName("is_ebook")
    var isEbook: Boolean? = false,
    @get:PropertyName("list_price")
    @set:PropertyName("list_price")
    var listPrice: ListPrice? = null,
    val offers: List<Offer>? = null,
    @get:PropertyName("retail_price_x")
    @set:PropertyName("retail_price_x")
    var retailPrice: RetailPriceX? = null,
    val saleability: String? = ""
)