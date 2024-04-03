package com.example.demo.controller.dto

import com.example.demo.controller.CardController
import com.example.demo.controller.UserController
import com.example.demo.model.*
import java.io.Serializable
import java.sql.Timestamp

data class PurchaseDTO (
    val id: Long,
    val purchaseTimestamp: Timestamp,
    val userId: Long,
    val packetsPurchased: Int,
    val baseAmount: Int,
    val creditCardId: Long
):Serializable{
    constructor(purchaseObject: Purchase): this(
        purchaseObject.getId(),
        purchaseObject.getCreatedAt(),
        purchaseObject.getUser().getId(),
        purchaseObject.getPacketsPurchased(),
        purchaseObject.getBaseAmount(),
        purchaseObject.getCreditCard().getId()
    )
}