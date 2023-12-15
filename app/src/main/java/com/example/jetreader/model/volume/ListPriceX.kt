package com.example.jetreader.model.volume

import com.google.firebase.firestore.PropertyName

data class ListPriceX(
    @get:PropertyName("amount_in_micros")
    @set:PropertyName("amount_in_micros")
    var amountInMicros: Long? = null,
    @get:PropertyName("currency_code")
    @set:PropertyName("currency_code")
    var currencyCode: String? = null
)