package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class ExchangeOfferDTO (
        private val id: Long,
        private val bidderId: Long,
        private val exchangeRequestId: Long,
        private val offeredCardId: Long,
        private val status: ExchangeOfferStatus,
        private val createdAt: Timestamp
): Serializable {

    constructor(exchangeOfferObject: ExchangeOffer): this(
            exchangeOfferObject.getId(),
            exchangeOfferObject.getBidder().getId(),
            exchangeOfferObject.getExchangeRequest().getId(),
            exchangeOfferObject.getOfferedCard().getId(),
            exchangeOfferObject.getStatus(),
            exchangeOfferObject.getCreatedAt()
    )
}