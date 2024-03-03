package com.example.demo.controller.dto

import com.example.demo.model.*
import java.sql.Timestamp

data class CreateExchangeCounterofferRequest (
        val id: Long,
        val offeredCard: Card,
        val status: ExchangeRequestStatus,
        val createdAt: Timestamp,
        val exchangeRequest: ExchangeRequest,
        val exchangeOffer: ExchangeOffer
)