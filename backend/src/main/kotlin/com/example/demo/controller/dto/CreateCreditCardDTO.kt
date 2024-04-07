package com.example.demo.controller.dto

import java.sql.Date

data class CreateCreditCardDTO (
    val expirationDate: Date,
    val cardNumber: String,
    val cardHolder: String,
    val bank: String,
    val cardType:String
)