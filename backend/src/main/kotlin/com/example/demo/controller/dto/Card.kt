package com.example.demo.controller.dto
import java.io.Serializable

class CardDTO (
    private val id: Long,
    private val name: String,
    private val playerNumber: Int,
    private val playerPosition: String
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

    fun getPlayerNumber(): Int {
        return this.playerNumber
    }
}
