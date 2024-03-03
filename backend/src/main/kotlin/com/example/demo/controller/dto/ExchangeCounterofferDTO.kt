package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class ExchangeCounterofferDTO(
        private val id: Long,
        private val offeredCard: Card,
        private val status: ExchangeRequestStatus,
        private val exchangeRequest: ExchangeRequest,
        private val exchangeOffer: ExchangeOffer,
        private val createdAt: Timestamp
): Serializable {

    constructor(exchangeCounterOffer: ExchangeCounteroffer): this(
            exchangeCounterOffer.getId(),
            exchangeCounterOffer.getOfferedCard(),
            exchangeCounterOffer.getStatus(),
            exchangeCounterOffer.getExchangeRequest(),
            exchangeCounterOffer.getExchangeOffer(),
            exchangeCounterOffer.getCreatedAt()
    )
}