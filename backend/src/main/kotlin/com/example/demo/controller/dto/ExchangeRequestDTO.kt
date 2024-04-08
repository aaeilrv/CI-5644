package com.example.demo.controller.dto

import com.example.demo.controller.CardController
import com.example.demo.controller.UserController
import com.example.demo.model.Card
import com.example.demo.model.ExchangeRequest
import java.io.Serializable
import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.model.User
import java.sql.Timestamp

data class ExchangeRequestDTO (
        val id: Long,
        val userId: Long,
        val requestedCardId: Long,
        val status: String,
        val createdAt: Timestamp
): Serializable {
    constructor(exchangeRequestObject: ExchangeRequest): this(
            exchangeRequestObject.id!!,
            exchangeRequestObject.requester.id!!,
            exchangeRequestObject.requestedCard.id!!,
            exchangeRequestObject.status.toString(),
            exchangeRequestObject.createdAt
    )
}