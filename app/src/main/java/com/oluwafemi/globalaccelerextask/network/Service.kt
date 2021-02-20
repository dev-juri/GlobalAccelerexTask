package com.oluwafemi.globalaccelerextask.network

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface Service {
    @GET("/{cardNumber}")
    fun getCardDetailsAsync(
        @Path("cardNumber", encoded = true) cardNumber: Long
    ) : Deferred<NetworkResponse>
}