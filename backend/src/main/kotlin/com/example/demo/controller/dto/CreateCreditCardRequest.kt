package com.example.demo.controller.dto

import com.example.demo.model.User
import java.sql.Date

data class CreateCreditCardRequest (
    val user: User,
    val expirationDate: Date,
    val cardNumber: String,
    val cardHolderName: String,
    val bank: String,
    val cardType:String
)