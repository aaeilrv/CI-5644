package com.example.demo.controller.dto

import com.example.demo.model.CreditCard
import java.io.Serializable
import java.sql.Date
import kotlin.coroutines.coroutineContext

data class CreditCardDTO(
    private val id:Long,
    private val userId:Long,
    private val expirationDate: Date,
    private val cardNumber:String,
    private val cardholderName:String,
    private val bank:String,
    private val cardType: String

):Serializable{
    fun getId():Long{
        return this.id
    }
    fun getUserId():Long{
        return this.userId
    }

    fun getExpirationDate():Date{
        return this.expirationDate
    }
    fun getCardNumber():String{
        val number = this.cardNumber
        val secret = "*****"
        return secret+number.drop(4)

    }

    constructor(creditCardObject:CreditCard): this(
        creditCardObject.getId(),
        creditCardObject.getUser().getId(),
        creditCardObject.getExpirationDate(),
        creditCardObject.getNumber(),
        creditCardObject.getCardName(),
        creditCardObject.getBank(),
        creditCardObject.getCardType().toString()
    )

}