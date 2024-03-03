package com.example.demo.controller.dto

import com.example.demo.model.ExchangeRequestStatus
import com.example.demo.model.User
import java.sql.Timestamp

data class CreateExchangeRequestRequest (
        val id: Long,
        val user: User,
        val requestedCardId: String,
        val requestStatus: ExchangeRequestStatus,
        val createdAt: Timestamp
)