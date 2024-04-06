package com.example.demo.controller.dto

import java.io.Serializable
import com.example.demo.model.Card

data class CardDTO (
    val id: Long,
    val name: String,
    val playerNumber: Short,
    val photoURL: String?,
    val playerPosition: String,
    val country: String
):Serializable {
    constructor(card: Card): this(
        card.id!!,
        card.name,
        card.playerNumber,
        card.photoURL,
        card.playerPosition.toString(),
        card.country
    )

}
