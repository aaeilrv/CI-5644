package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class ExchangeCounterofferDTO(
        private val id: Long,
        private val offeredCardId: Long,
        private val status: ExchangeRequestStatus,
        private val exchangeRequestId: Long,
        private val exchangeOfferId: Long,
        private val createdAt: Timestamp
): Serializable {

    constructor(exchangeCounterOffer: ExchangeCounteroffer): this(
            exchangeCounterOffer.getId(),
            exchangeCounterOffer.getOfferedCard().getId(),
            exchangeCounterOffer.getStatus(),
            exchangeCounterOffer.getExchangeRequest().getId(),
            exchangeCounterOffer.getExchangeOffer().getId(),
            exchangeCounterOffer.getCreatedAt()
    )
}