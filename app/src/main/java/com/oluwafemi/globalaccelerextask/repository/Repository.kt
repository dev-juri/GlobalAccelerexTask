package com.oluwafemi.globalaccelerextask.repository

import com.oluwafemi.globalaccelerextask.NetworkResult
import com.oluwafemi.globalaccelerextask.domain.CardDetails
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun fetchCardDetails(cardNumber: Long) : NetworkResult<CardDetails>
}