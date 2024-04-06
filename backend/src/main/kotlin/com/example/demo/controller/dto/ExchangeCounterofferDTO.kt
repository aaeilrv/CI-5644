package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

data class ExchangeCounterofferDTO(
        val id: Long,
        val offeredCardId: Long,
        val status: ExchangeRequestStatus,
        val exchangeRequestId: Long,
        val exchangeOfferId: Long,
        val createdAt: Timestamp
): Serializable {
    constructor(exchangeCounterOffer: ExchangeCounteroffer): this(
            exchangeCounterOffer.id!!,
            exchangeCounterOffer.card.id!!,
            exchangeCounterOffer.status,
            exchangeCounterOffer.exchangeRequest.id!!,
            exchangeCounterOffer.exchangeOffer.id!!,
            exchangeCounterOffer.createdAt
    )
}