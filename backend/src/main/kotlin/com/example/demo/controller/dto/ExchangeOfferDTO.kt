package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class ExchangeOfferDTO (
        private val id: Long,
        private val user: User,
        private val exchangeRequest: ExchangeRequest,
        private val offeredCard: Card,
        private val status: ExchangeOfferStatus,
        private val createdAt: Timestamp
): Serializable {

    constructor(exchangeOfferObject: ExchangeOffer): this(
            exchangeOfferObject.getId(),
            exchangeOfferObject.getUser(),
            exchangeOfferObject.getExchangeRequest(),
            exchangeOfferObject.getOfferedCard(),
            exchangeOfferObject.getStatus(),
            exchangeOfferObject.getCreatedAt()
    )
}