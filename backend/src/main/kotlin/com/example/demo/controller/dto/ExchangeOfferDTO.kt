package com.example.demo.controller.dto

import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp
import java.util.Date

data class ExchangeOfferDTO (
        val id: Long,
        val bidderId: Long,
        val bidderUsername: String,
        val exchangeRequestId: Long,
        val offeredCardId: Long,
        val status: String,
        val createdAt: Timestamp
): Serializable {
    constructor(exchangeOfferObject: ExchangeOffer): this(
            exchangeOfferObject.id!!,
            exchangeOfferObject.bidder.id!!,
            exchangeOfferObject.bidder.username,
            exchangeOfferObject.exchangeRequest.id!!,
            exchangeOfferObject.offeredCard.id!!,
            exchangeOfferObject.status.toString(),
            exchangeOfferObject.createdAt
    )
}