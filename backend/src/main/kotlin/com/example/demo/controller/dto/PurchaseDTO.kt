package com.example.demo.controller.dto

import com.example.demo.controller.CardController
import com.example.demo.controller.UserController
import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class PurchaseDTO (
    private val id: Long,
    private val purchaseTimestamp: Timestamp,
    private val userId: Long,
    private val packetsPurchased: Int,
    private val baseAmount: Int,
    private val creditCardId: Long
):Serializable{
    fun getId(): Long {
        return this.id
    }

    fun getUserId():Long{
        return this.userId
    }

    fun getTimestamp():Timestamp{
        return this.purchaseTimestamp
    }

    fun getPacketsPurchased():Int{
        return this.packetsPurchased
    }

    fun getAmount():Int{
        return this.baseAmount
    }

    fun getCreditCardId():Long{
        return this.creditCardId
    }

    constructor(purchaseObject: Purchase): this(
        purchaseObject.getId(),
        purchaseObject.getCreatedAt(),
        purchaseObject.getUser().getId(),
        purchaseObject.getPacketsPurchased(),
        purchaseObject.getBaseAmount(),
        purchaseObject.getCreditCard().getId()
    )
}