package com.example.demo.controller.dto

import com.example.demo.model.CreditCard
import java.io.Serializable
import java.sql.Date
import kotlin.coroutines.coroutineContext

data class CreditCardDTO(
    val id:Long,
    val userId:Long,
    val expirationDate: Date,
    val cardNumber:String,
    val cardholderName:String,
    val bank:String,
    val cardType: String

):Serializable{
    constructor(creditCardObject:CreditCard): this(
        creditCardObject.id!!,
        creditCardObject.cardHolder.id!!,
        creditCardObject.expirationDate,
        creditCardObject.cardNumber,
        creditCardObject.cardholderName,
        creditCardObject.bank,
        creditCardObject.card_type.toString()
    )

}