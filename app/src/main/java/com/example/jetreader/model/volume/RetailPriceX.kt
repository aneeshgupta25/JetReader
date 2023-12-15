package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class RetailPriceX(
    val amount: Double? = null,
    @get:PropertyName("currency_code")
    @set:PropertyName("currency_code")
    var currencyCode: String? = null
)