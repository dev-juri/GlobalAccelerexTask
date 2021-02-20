package com.oluwafemi.globalaccelerextask.domain

data class CardDetails(
    val cardScheme: String,
    val cardType: String,
    val bank: String,
    val country: String,
    val cardLengthNumber: Int,
    val prepaid: Boolean
)