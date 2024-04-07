package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

data class ExchangeOfferDTO (
        private val id: Long,
        private val bidderId: Long,
        private val exchangeRequestId: Long,
        private val offeredCardId: Long,
        private val requestedCardName: String,
        private val offeredCardName: String,
        private val status: String,
        private val createdAt: Timestamp
): Serializable {

    fun getId(): Long {
        return this.id
    }

    fun getBidderId(): Long {
        return this.bidderId
    }

    fun getExchangeRequestId(): Long {
        return this.exchangeRequestId
    }

    fun getOfferedCardName(): String {
        return this.offeredCardName
    }

    fun getOfferedCardId(): Long {
        return this.offeredCardId
    }

    fun getRequestedCardName(): String {
        return this.requestedCardName
    }

    fun getStatus(): String {
        return this.status
    }

    fun getCreatedAt(): Date {
        return this.createdAt
    }

    constructor(exchangeOfferObject: ExchangeOffer): this(
            exchangeOfferObject.getId(),
            exchangeOfferObject.getBidder().getId(),
            exchangeOfferObject.getExchangeRequest().getId(),
            exchangeOfferObject.getOfferedCard().getId(),
            exchangeOfferObject.getExchangeRequest().getRequestedCard().getName(),
            exchangeOfferObject.getOfferedCard().getName(),
            exchangeOfferObject.getRequestStatus().toString(),
            exchangeOfferObject.getCreatedAt()
    )
}