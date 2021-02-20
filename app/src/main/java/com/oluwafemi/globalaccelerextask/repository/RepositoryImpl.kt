package com.oluwafemi.globalaccelerextask.repository

import com.oluwafemi.globalaccelerextask.NetworkResult
import com.oluwafemi.globalaccelerextask.asDomainModel
import com.oluwafemi.globalaccelerextask.domain.CardDetails
import com.oluwafemi.globalaccelerextask.network.Service
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: Service) : Repository {
    override suspend fun fetchCardDetails(cardNumber: Long): NetworkResult<CardDetails> {
        return try {
            val request = service.getCardDetailsAsync(cardNumber).await()

            NetworkResult.Success(request.asDomainModel())
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }

}