package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class ListPrice(
    val amount: Double? = 0.0,
    @get:PropertyName("currency_code")
    @set:PropertyName("currency_code")
    var currencyCode: String? = "in"
)