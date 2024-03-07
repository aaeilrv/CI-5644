package com.example.demo.controller.dto

import com.example.demo.model.*
import java.sql.Timestamp

data class UpdateExchangeRequestRequest (
        val id: Long,
        val user: User,
        val requestedCard: Card,
        val requestStatus: ExchangeRequestStatus,
        val createdAt: Timestamp
)