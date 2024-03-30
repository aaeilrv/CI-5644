package com.example.demo.controller.dto;

import java.sql.Timestamp
import com.example.demo.model.User
import com.example.demo.model.CreditCard

data class CreatePurchaseDTO (
    val userId: Long,
    val amount: Int,
    val creditCardId: Long,
    val createdAt: Timestamp?
)
