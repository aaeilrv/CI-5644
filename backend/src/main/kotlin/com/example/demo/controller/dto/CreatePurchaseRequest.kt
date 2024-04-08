package com.example.demo.controller.dto

import java.sql.Timestamp
import com.example.demo.model.User
import com.example.demo.model.CreditCard

data class CreatePurchaseRequest (
    val user: User,
    val amount: Int,
    val creditCard: CreditCard,
    val createdAt: Timestamp
)