package com.example.demo.controller.dto

import java.io.Serializable
import com.example.demo.model.Card

data class CardDTO (
    private val id: Long,
    private val name: String,
    private val playerNumber: Short,
    private val photoURL: String?,
    private val playerPosition: String,
    private val country: String
):Serializable {
    fun getId(): Long {
        return this.id
    }

    fun getName(): String {
        return this.name
    }

    fun getPlayerPosition(): String {
        return this.playerPosition
    }

    fun getPlayerNumber(): Short {
        return this.playerNumber
    }

    fun getPhotoURL(): String? {
        return this.photoURL
    }

    fun getCountry(): String {
        return this.country
    }

    constructor(card: Card): this(
        card.getId(),
        card.getName(),
        card.getPlayerNumber(),
        card.getPhotoURL(),
        card.getPlayerPosition().toString(),
        card.getCountry()
    )

}
