package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

data class ExchangeCounterofferDTO(
        private val id: Long,
        private val counterOfferedCardName: String,
        private val status: ExchangeRequestStatus,
        private val exchangeRequestId: Long,
        private val requestCardName: String,
        private val exchangeOfferId: Long,
        private val offeredCardName: String,
        private val counterOfferedCardId: Long,
        private val createdAt: Timestamp
): Serializable {
    fun getId(): Long {
        return this.id
    }

    fun getCounterOfferedCardName(): String {
        return this.counterOfferedCardName
    }

    fun getCounterOfferedCardId(): Long {
        return this.counterOfferedCardId
    }

    fun getOfferedCardName(): String {
        return this.offeredCardName
    }

    fun getRequestCardName(): String {
        return this.requestCardName
    }

    fun getStatus(): ExchangeRequestStatus {
        return this.status
    }

    fun getExchangeRequestId(): Long {
        return this.exchangeRequestId
    }

    fun getExchangeOfferId(): Long {
        return this.exchangeOfferId
    }

    fun getCreatedAt(): Timestamp {
        return this.createdAt
    }

    constructor(exchangeCounterOffer: ExchangeCounteroffer): this(
            exchangeCounterOffer.getId(),
            exchangeCounterOffer.getOfferedCard().getName(),
            exchangeCounterOffer.getRequestStatus(),
            exchangeCounterOffer.getExchangeRequest().getId(),
            exchangeCounterOffer.getExchangeRequest().getRequestedCard().getName(),
            exchangeCounterOffer.getExchangeOffer().getId(),
            exchangeCounterOffer.getExchangeOffer().getOfferedCard().getName(),
            exchangeCounterOffer.getOfferedCard().getId(),
            exchangeCounterOffer.getCreatedAt()
    )
}