package com.example.demo.controller.dto

import com.example.demo.model.Card
import java.io.Serializable
import com.example.demo.model.ExchangeRequest
import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.model.User
import java.sql.Timestamp

data class ExchangeRequestDTO (
        private val id: Long,
        private val user: User,
        private val requestedCard: Card,
        private val status: ExchangeRequestStatus,
        private val createdAt: Timestamp
): Serializable {

    constructor(exchangeRequestObject: ExchangeRequest): this(
            exchangeRequestObject.getId(),
            exchangeRequestObject.getUser(),
            exchangeRequestObject.getRequestedCard(),
            exchangeRequestObject.getStatus(),
            exchangeRequestObject.getCreatedAt()
    )
}