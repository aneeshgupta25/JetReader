package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class Offer(
    @get:PropertyName("frinsky_offer_type")
    @set:PropertyName("frinsky_offer_type")
    var finskyOfferType: Int? = 0,
    @get:PropertyName("list_price_x")
    @set:PropertyName("list_price_x")
    var listPrice: ListPriceX? = null,
    @get:PropertyName("retail_price")
    @set:PropertyName("retail_price")
    var retailPrice: RetailPrice? = null
)